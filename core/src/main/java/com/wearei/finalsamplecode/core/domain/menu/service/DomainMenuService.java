package com.wearei.finalsamplecode.core.domain.menu.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
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

    public Menu createMenu(Long userId, Long storeId, String menuName, Long price) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_OWNER)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

        Store store = storeRepository.findById(storeId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        return menuRepository.save(new Menu(
                store,
                menuName,
                price,
                user
        ));
    }

    // 메뉴 수정
    public Menu updateMenu(Long userId, Long menuId, Long storeId, String menuName, Long price) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_OWNER)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

        Store store = storeRepository.findById(storeId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        Menu menu = checkMenu(menuId);

        menu.update(menuName, price);

        return menu;
    }

    // 메뉴 삭제
    public void deleteMenu(Long userId, Long menuId, Long storeId) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_OWNER)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

        Store store = storeRepository.findById(storeId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        Menu menu = checkMenu(menuId);

        menuRepository.delete(menu);
    }

    // 메뉴 확인
    public Menu checkMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(()
        -> new ApiException(ErrorStatus._NOT_FOUND_MENU));
    }
}
