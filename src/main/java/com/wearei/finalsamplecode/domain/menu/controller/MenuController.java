package com.wearei.finalsamplecode.domain.menu.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.menu.dto.request.CreateMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.request.DeleteMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.request.UpdateMenuRequest;
import com.wearei.finalsamplecode.domain.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.dto.response.UpdateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ApiResponse<CreateMenuResponse> createMenu(@RequestBody CreateMenuRequest request, @AuthenticationPrincipal AuthUser authUser) {
        CreateMenuResponse createMenuResponse = menuService.createMenu(request, authUser);
        return ApiResponse.onSuccess(createMenuResponse);
    }

    @PatchMapping("/{menuId}")
    public ApiResponse<UpdateMenuResponse> updateMenu(@PathVariable Long menuId, @RequestBody UpdateMenuRequest request, @AuthenticationPrincipal AuthUser authUser){
        UpdateMenuResponse updateMenuResponse = menuService.updateMenu(menuId, request, authUser);
        return ApiResponse.onSuccess(updateMenuResponse);
    }

    @DeleteMapping("/{menuId}")
    public void deleteMenu(@PathVariable Long menuId, @AuthenticationPrincipal AuthUser authUser, @RequestBody DeleteMenuRequest request){
        menuService.deleteMenu(menuId, authUser, request);
    }
}
