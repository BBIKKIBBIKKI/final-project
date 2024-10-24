package com.wearei.finalsamplecode.domain.menu.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.menu.dto.request.CreateMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.request.DeleteMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.request.UpdateMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.dto.response.UpdateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public CreateMenuResponse createMenu(CreateMenuRequest request, AuthUser authUser) {
        User user = User.fromAuthUser(authUser);

        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(!store.getIsDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        // 사장님 권한인지 확인
        authCheck(authUser, store);

        Menu menu = new Menu(
                store,
                request.getMenuName(),
                request.getPrice(),
                user
        );
        Menu savedMenu = menuRepository.save(menu);

        return new CreateMenuResponse(
                savedMenu.getStore().getStoreName(),
                savedMenu.getMenuName()
        );
    }

    // 메뉴 수정
    public UpdateMenuResponse updateMenu(Long menuId, UpdateMenuRequest request, AuthUser authUser) {
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(!store.getIsDeleted()) {
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        authCheck(authUser, store);

        Menu menu = checkMenu(menuId);

        menu.update(request.getMenuName(), request.getPrice());

        return new UpdateMenuResponse(menu.getMenuName());
    }

    // 메뉴 삭제
    public void deleteMenu(Long menuId, AuthUser authUser, DeleteMenuRequest request) {
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(!store.getIsDeleted()) {
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
        if(!Objects.equals(store.getUser().getUserId(), authUser.getUserId())){
            throw new ApiException(ErrorStatus._BAD_REQUEST_STORE);
        }
    }

    public List<Menu> findAllMenus(){
        return menuRepository.findAll();
    }
}