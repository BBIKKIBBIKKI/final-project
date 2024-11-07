package com.wearei.finalsamplecode.api.menu;

import com.wearei.finalsamplecode.api.menu.dto.request.CreateMenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.request.DeleteMenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.request.UpdateMenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.api.menu.dto.response.UpdateMenuResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.service.DomainMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuApi {
    private final DomainMenuService domainMenuService;

    @PostMapping
    public ApiResponse<CreateMenuResponse> createMenu(@RequestBody CreateMenuRequest request, @AuthenticationPrincipal AuthUser authUser) {
        Menu menu = domainMenuService.createMenu(request.getStoreId(),request.getMenuName(), request.getPrice(), authUser);
        return ApiResponse.onSuccess(new CreateMenuResponse(menu));
    }

    @PatchMapping("/{menuId}")
    public ApiResponse<UpdateMenuResponse> updateMenu(@PathVariable Long menuId, @RequestBody UpdateMenuRequest request, @AuthenticationPrincipal AuthUser authUser){
        Menu menu = domainMenuService.updateMenu(menuId, request.getStoreId(), request.getMenuName(), request.getPrice(),authUser);
        return ApiResponse.onSuccess(new UpdateMenuResponse(menu));
    }

    @DeleteMapping("/{menuId}")
    public ApiResponse<String> deleteMenu(@PathVariable Long menuId, @AuthenticationPrincipal AuthUser authUser, @RequestBody DeleteMenuRequest request){
        domainMenuService.deleteMenu(menuId, authUser, request.getStoreId());
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
