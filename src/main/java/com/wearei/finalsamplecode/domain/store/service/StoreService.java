package com.wearei.finalsamplecode.domain.store.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreCreateRequest;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreUpdateRequest;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreCreateResponse;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreIntegratedResponse;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreUpdateResponse;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 가게 생성
    public StoreCreateResponse createStore(StoreCreateRequest request, AuthUser authUser) {
        User user = checkUser(authUser.getId());

        authCheck(authUser);

        Store store = new Store(
                request.getStoreName(),
                request.getOpenedAt(),
                request.getClosedAt(),
                user
        );
        Store savedStore = storeRepository.save(store);

        return new StoreCreateResponse(
                savedStore.getStoreName(),
                savedStore.getOpenedAt(),
                savedStore.getClosedAt()
        );
    }

    // 가게 수정
    public StoreUpdateResponse updateStore(StoreUpdateRequest request, AuthUser authUser, Long storeId) {

        Store store = checkStore(storeId);

        authCheck(authUser);

        store.update(request.getStoreName(), request.getOpenedAt(), request.getClosedAt());

        return new StoreUpdateResponse(store.getStoreName(), store.getOpenedAt(), store.getClosedAt());
    }


    // 통합검색 (가게 + 메뉴)
    @Transactional(readOnly = true)
    public List<StoreIntegratedResponse> searchStoresOrMenus(String storeName, String menuName) {
        List<Store> stores = storeRepository.searchStoresOrMenu(storeName, menuName);
        List<StoreIntegratedResponse> storeList = new ArrayList<>();
        for (Store store : stores) {
            StoreIntegratedResponse response = new StoreIntegratedResponse(store.getStoreName(), store.getOpenedAt(), store.getClosedAt(), store.getIsDeleted());
            storeList.add(response);
        }
        return storeList;
    }


    // 가게 삭제
    public void deleteStore(Long storeId, AuthUser authUser) {

        Store store = checkStore(storeId);

        authCheck(authUser);

        store.softDelete();
        storeRepository.save(store);

    }

    // 유저 확인
    private User checkUser(Long userId){
        return userRepository.findById(userId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }

    // 가게 확인
    private Store checkStore(Long storeId){
        Store store =  storeRepository.findById(storeId).orElseThrow(()
        -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(!store.getIsDeleted()){
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        return store;
    }

    // 가게 사장님 권한 확인
    public void authCheck(AuthUser authUser) {
        if (!authUser.getUserRole().equals(UserRole.OWNER)){
            throw new ApiException(ErrorStatus._BAD_REQUEST_STORE);
        }
    }
}
