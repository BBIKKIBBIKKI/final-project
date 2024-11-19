package com.wearei.finalsamplecode.core.domain.menu.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.lock.service.StockService;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DomainMenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final DomainStoreService domainStoreService;
    private final StockService stockService;
    private final RedissonClient redissonClient;

    public Menu createMenu(Long userId, Long storeId, String menuName, Long price, Long inventory) {

        User user = checkOwner(userId);

        Store store = domainStoreService.checkStore(storeId);

        Menu savedMenu = menuRepository.save(new Menu(
                store,
                menuName,
                price,
                user,
                inventory
        ));

        // Redis에 초기 재고 설정
        String key = stockService.keyResolver("menu", savedMenu.getId().toString());
        stockService.setStock(key, inventory.intValue());

        return savedMenu;
    }

    // 메뉴 수정
    public Menu updateMenu(Long userId, Long menuId, Long storeId, String menuName, Long price) {

        checkOwner(userId);

        domainStoreService.checkStore(storeId);

        Menu menu = checkMenu(menuId);

        menu.update(menuName, price);

        return menu;
    }

    // 메뉴 삭제
    public void deleteMenu(Long userId, Long menuId, Long storeId) {

        checkOwner(userId);

        domainStoreService.checkStore(storeId);

        Menu menu = checkMenu(menuId);

        menuRepository.delete(menu);
    }

    // 메뉴 확인
    public Menu checkMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_MENU));
    }

    public User checkOwner(Long userId){
        User user = userRepository.findByIdOrThrow(userId);

        Preconditions.validate(!user.isNotSameRole(UserRole.ROLE_OWNER), ErrorStatus._NOT_OWNER_USER);

        return user;
    }

    // 재고감소 락 O
    public void decreaseStockWithLock(Long menuId, long count) {
        String key = stockService.keyResolver("menu", menuId.toString());
        final String lockKey = key + ":lock";
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(10, 10, TimeUnit.SECONDS)) {
                int stock = stockService.currentStock(key);

                // 재고 부족 또는 소진 처리
                if (stock <= 0) {
                    log.info("재고가 소진되었습니다. (menuId: {}, 현재 재고: {})", menuId, stock);
                    throw new ApiException(ErrorStatus._INSUFFICIENT_INVENTORY); // 예외를 던져 주문을 중단
                }
                if (stock < count) {
                    log.info("재고가 부족하여 주문을 처리할 수 없습니다. (menuId: {}, 현재 재고: {}, 주문 수량: {})",
                            menuId, stock, count);
                    throw new ApiException(ErrorStatus._INSUFFICIENT_INVENTORY); // 예외를 던져 주문을 중단
                }

                // 재고 감소 후 업데이트
                stockService.setStock(key, stock - (int) count);
                log.info("주문 처리 완료. (menuId: {}, 감소된 재고: {}, 남은 재고: {})",
                        menuId, count, stock - count);
            } else {
                throw new ApiException(ErrorStatus._LOCK_ERROR); // 락을 얻지 못한 경우
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 복구
            throw new ApiException(ErrorStatus._LOCK_ERROR);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    // 재고감소 락 X
    public void decreaseStockWithoutLock(Long menuId, long count) {
        String key = stockService.keyResolver("menu", menuId.toString());
        stockService.decreaseNoLock(key, (int) count);
    }
}
