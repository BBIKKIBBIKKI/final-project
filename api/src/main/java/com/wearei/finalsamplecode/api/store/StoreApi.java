package com.wearei.finalsamplecode.api.store;

import com.wearei.finalsamplecode.api.store.dto.request.StoreRequest;
import com.wearei.finalsamplecode.api.store.dto.response.*;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.security.AuthUser;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import jakarta.validation.Valid;
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
    public ApiResponse<StoreResponse.Create> createStore(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody StoreRequest.Create request) {
        return ApiResponse.onSuccess(new StoreResponse.Create(domainStoreService.createStore(authUser.getUserId(), request.groundId(), request.storeName(), request.openedAt(), request.closedAt())));
    }

    // 가게 수정 (사장님 권한)
    @PatchMapping("/{storeId}")
    public ApiResponse<StoreResponse.Update> updateStore(@AuthenticationPrincipal AuthUser authUser,@PathVariable Long storeId, @RequestBody StoreRequest.Update request){
        return ApiResponse.onSuccess(new StoreResponse.Update(domainStoreService.updateStore(authUser.getUserId(), request.groundId(), request.storeName(), request.openedAt(), request.closedAt(), storeId)));
    }

    // 가게 다건 조회
    @GetMapping
    public ApiResponse<List<StoreResponse.GetAll>> getAllStores(){
        List<Store> stores = defaultStoreService.getAllStores();
        List<StoreResponse.GetAll> storeList = stores.stream().map(StoreResponse.GetAll::new).toList();
        return ApiResponse.onSuccess(storeList);
    }

    // 가게 단건 조회(메뉴도 같이 나옴)
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResponse.GetWithMenus> getStoreAndMenu(@PathVariable Long storeId){
        return ApiResponse.onSuccess(new StoreResponse.GetWithMenus(defaultStoreService.getStore(storeId)));
    }

    // 가게 + 메뉴 통합 검색 기능
    @GetMapping("/search")
    public ApiResponse<List<StoreResponse.Integrated>> searchStoresOrMenus(@RequestParam(required = false) String storeName,
                                                                          @RequestParam(required = false) String menuName){

        List<Store> stores = defaultStoreService.searchStoresOrMenus(storeName, menuName);
        List<StoreResponse.Integrated> responses = stores.stream()
                .map(store -> new StoreResponse.Integrated(
                        store.getStoreName(),
                        store.getOpenedAt(),
                        store.getClosedAt(),
                        store.isDeleted()
                )).toList();
        return ApiResponse.onSuccess(responses);
    }

    // 가게 삭제 (사장님 권한)
    @DeleteMapping("/{storeId}")
    public ApiResponse<Void> deleteStore(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long storeId){
        domainStoreService.deleteStore(authUser.getUserId(), storeId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}
