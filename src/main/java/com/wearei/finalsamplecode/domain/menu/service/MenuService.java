package com.wearei.finalsamplecode.domain.menu.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.menu.dto.request.CreateMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.request.UpdateMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.dto.response.UpdateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.service.StoreService;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreService storeService;

    public CreateMenuResponse createMenu(CreateMenuRequest request, AuthUser authUser) {
        User user = User.fromAuthUser(authUser);

        storeService.authCheck(authUser);

        Store store = storeService.checkStore(request.getStoreId());

        Menu menu = new Menu(
                store,
                request.getMenuName(),
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
        User user = User.fromAuthUser(authUser);

        storeService.authCheck(authUser);

        Store store = storeService.checkStore(request.getStoreId());

        Menu menu = checkMenu(menuId);

        menu.update(request.getMenuName());

        return new UpdateMenuResponse(menu.getMenuName());
    }



    // 메뉴 확인
    private Menu checkMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(()
        -> new ApiException(ErrorStatus._NOT_FOUND_MENU));
    }


    public List<Menu> findAllMenus(){
        return menuRepository.findAll();
    }
}