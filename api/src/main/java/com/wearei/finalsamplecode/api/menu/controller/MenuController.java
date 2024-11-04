package com.wearei.finalsamplecode.api.menu.controller;

import com.wearei.finalsamplecode.api.menu.dto.request.CreateMenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.request.DeleteMenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.request.UpdateMenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.api.menu.dto.response.UpdateMenuResponse;
import com.wearei.finalsamplecode.api.menu.service.MenuService;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ApiResponse<CreateMenuResponse> createMenu(@RequestBody CreateMenuRequest request, @AuthenticationPrincipal AuthUser authUser) {
        return ApiResponse.onSuccess(menuService.createMenu(request, authUser));
    }

    @PatchMapping("/{menuId}")
    public ApiResponse<UpdateMenuResponse> updateMenu(@PathVariable Long menuId, @RequestBody UpdateMenuRequest request, @AuthenticationPrincipal AuthUser authUser){
        return ApiResponse.onSuccess(menuService.updateMenu(menuId, request, authUser));
    }

    @DeleteMapping("/{menuId}")
    public ApiResponse<String> deleteMenu(@PathVariable Long menuId, @AuthenticationPrincipal AuthUser authUser, @RequestBody DeleteMenuRequest request){
        menuService.deleteMenu(menuId, authUser, request);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
