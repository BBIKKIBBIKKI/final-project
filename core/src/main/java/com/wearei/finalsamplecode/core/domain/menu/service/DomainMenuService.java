package com.wearei.finalsamplecode.core.domain.menu.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DomainMenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public Menu createMenu(Long storeId, String menuName, Long price, AuthUser authUser) {
        User user = User.fromAuthUser(authUser);

        Store store = storeRepository.findById(storeId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        // 사장님 권한인지 확인
        authCheck(authUser, store);


        return menuRepository.save(new Menu(
                store,
                menuName,
                price,
                user
        ));
    }

    // 메뉴 수정
    public Menu updateMenu(Long menuId, Long storeId, String menuName, Long price, AuthUser authUser) {
        Store store = storeRepository.findById(storeId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        authCheck(authUser, store);

        Menu menu = checkMenu(menuId);

        menu.update(menuName, price);

        return menu;
    }

    // 메뉴 삭제
    public void deleteMenu(Long menuId, AuthUser authUser, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        authCheck(authUser, store);

        Menu menu = checkMenu(menuId);

        menuRepository.delete(menu);
    }

    // 메뉴 확인
    public Menu checkMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(()
        -> new ApiException(ErrorStatus._NOT_FOUND_MENU));
    }

    // 가게 사장님 권한 확인
    private void authCheck(AuthUser authUser, Store store) {
        if(!Objects.equals(store.getUser().getId(), authUser.getUserId())){
            throw new ApiException(ErrorStatus._BAD_REQUEST_STORE);
        }
    }
}
