package com.wearei.finalsamplecode.api.menu;

import com.wearei.finalsamplecode.api.menu.dto.request.MenuRequest;
import com.wearei.finalsamplecode.api.menu.dto.response.MenuResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.menu.service.DomainMenuService;
import com.wearei.finalsamplecode.security.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuApi {
    private final DomainMenuService domainMenuService;

    @PostMapping
    public ApiResponse<MenuResponse.Create> createMenu(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody MenuRequest.Create request) {
        return ApiResponse.onSuccess(new MenuResponse.Create(domainMenuService.createMenu(authUser.getUserId(), request.storeId(),request.menuName(), request.price())));
    }

    @PatchMapping("/{menuId}")
    public ApiResponse<MenuResponse.Update> updateMenu(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long menuId, @RequestBody MenuRequest.Update request){
        return ApiResponse.onSuccess(new MenuResponse.Update(domainMenuService.updateMenu(authUser.getUserId(), menuId, request.storeId(), request.menuName(), request.price())));
    }

    @DeleteMapping("/{menuId}")
    public ApiResponse<Void> deleteMenu(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long menuId, @RequestBody MenuRequest.Delete request){
        domainMenuService.deleteMenu(authUser.getUserId(), menuId, request.storeId());
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
