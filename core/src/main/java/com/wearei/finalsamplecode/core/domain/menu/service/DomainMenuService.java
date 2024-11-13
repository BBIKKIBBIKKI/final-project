package com.wearei.finalsamplecode.core.domain.menu.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DomainMenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final DomainStoreService domainStoreService;
    public Menu createMenu(Long userId, Long storeId, String menuName, Long price) {

        User user = checkOwner(userId);

        Store store = domainStoreService.checkStore(storeId);

        return menuRepository.save(new Menu(
                store,
                menuName,
                price,
                user
        ));
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
}
