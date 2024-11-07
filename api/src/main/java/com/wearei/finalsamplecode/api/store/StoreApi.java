package com.wearei.finalsamplecode.api.store;

import com.wearei.finalsamplecode.api.store.dto.request.StoreCreateRequest;
import com.wearei.finalsamplecode.api.store.dto.request.StoreUpdateRequest;
import com.wearei.finalsamplecode.api.store.dto.response.*;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.security.AuthUser;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreApi {
    private final DomainStoreService domainStoreService;
    private final DefaultStoreService defaultStoreService;

    // 가게 생성 (사장님 권한)
    @PostMapping
    public ApiResponse<StoreCreateResponse> createStore(@AuthenticationPrincipal AuthUser authUser, @RequestBody StoreCreateRequest request) {
        Store store = domainStoreService.createStore(authUser.getUserId(), request.getGroundId(), request.getStoreName(), request.getOpenedAt(), request.getClosedAt());
        return ApiResponse.onSuccess(new StoreCreateResponse(store));
    }

    // 가게 수정 (사장님 권한)
    @PatchMapping("/{storeId}")
    public ApiResponse<StoreUpdateResponse> updateStore(@RequestBody StoreUpdateRequest request, @AuthenticationPrincipal AuthUser authUser, @PathVariable Long storeId){
        Store store = domainStoreService.updateStore(authUser.getUserId(), request.getGroundId(), request.getStoreName(), request.getOpenedAt(), request.getClosedAt(), storeId);
        return ApiResponse.onSuccess(new StoreUpdateResponse(store));
    }

    // 가게 다건 조회
    @GetMapping
    public ApiResponse<List<StoreGetAllResponse>> getAllStores(){
        List<Store> stores = defaultStoreService.getAllStores();
        List<StoreGetAllResponse> storeList = stores.stream().map(StoreGetAllResponse::new).toList();
        return ApiResponse.onSuccess(storeList);
    }

    // 가게 단건 조회(메뉴도 같이 나옴)
    @GetMapping("/{storeId}")
    public ApiResponse<StoreGetResponse> getStoreAndMenu(@PathVariable Long storeId){
        Store store = defaultStoreService.getStore(storeId);
        return ApiResponse.onSuccess(new StoreGetResponse(store));
    }

    // 가게 + 메뉴 통합 검색 기능
    @GetMapping("/search")
    public ApiResponse<List<StoreIntegratedResponse>> searchStoresOrMenus(@RequestParam(required = false) String storeName,
                                                                          @RequestParam(required = false) String menuName){

        List<Store> stores = defaultStoreService.searchStoresOrMenus(storeName, menuName);
        List<StoreIntegratedResponse> responses = stores.stream()
                .map(store -> new StoreIntegratedResponse(
                        store.getStoreName(),
                        store.getOpenedAt(),
                        store.getClosedAt(),
                        store.isDeleted()
                )).toList();
        return ApiResponse.onSuccess(responses);
    }

    // 가게 삭제 (사장님 권한)
    @DeleteMapping("/{storeId}")
    public ApiResponse<String> deleteStore(@PathVariable Long storeId, @AuthenticationPrincipal AuthUser authUser){
        domainStoreService.deleteStore(authUser.getUserId(), storeId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
