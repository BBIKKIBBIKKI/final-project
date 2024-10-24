package com.wearei.finalsamplecode.domain.store.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreCreateRequest;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreUpdateRequest;
import com.wearei.finalsamplecode.domain.store.dto.response.*;
import com.wearei.finalsamplecode.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    // 가게 생성 (사장님 권한)
    @PostMapping
    public ApiResponse<StoreCreateResponse> createStore(@RequestBody StoreCreateRequest request, @AuthenticationPrincipal AuthUser authUser) {
        StoreCreateResponse storeCreateResponse = storeService.createStore(request, authUser);
        return ApiResponse.onSuccess(storeCreateResponse);
    }

    // 가게 수정 (사장님 권한)
    @PatchMapping("/{storeId}")
    public ApiResponse<StoreUpdateResponse> updateStore(@RequestBody StoreUpdateRequest request, @AuthenticationPrincipal AuthUser authUser, @PathVariable Long storeId) {
        StoreUpdateResponse storeUpdateResponse = storeService.updateStore(request, authUser, storeId);
        return ApiResponse.onSuccess(storeUpdateResponse);
    }

    // 가게 다건 조회
    @GetMapping
    public ApiResponse<List<StoreGetAllResponse>> getAllStores() {
        return ApiResponse.onSuccess(storeService.getAllStores());
    }

    // 가게 단건 조회(메뉴도 같이 나옴)
    @GetMapping("/{storeId}")
    public ApiResponse<StoreGetResponse> getStoreAndMenu(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(storeService.getStore(storeId));
    }

    // 가게 + 메뉴 통합 검색 기능
    @GetMapping("/search")
    public ApiResponse<List<StoreIntegratedResponse>> searchStoresOrMenus(@RequestParam(required = false) String storeName,
                                                                          @RequestParam(required = false) String menuName) {

        return ApiResponse.onSuccess(storeService.searchStoresOrMenus(storeName, menuName));
    }

    // 가게 삭제 (사장님 권한)
    @DeleteMapping("/{storeId}")
    public ApiResponse<String> deleteStore(@PathVariable Long storeId, @AuthenticationPrincipal AuthUser authUser) {
        storeService.deleteStore(storeId, authUser);
        return ApiResponse.onSuccess("정상 삭제되었습니다.");
    }
}
