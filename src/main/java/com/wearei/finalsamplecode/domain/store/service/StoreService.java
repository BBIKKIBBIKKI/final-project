package com.wearei.finalsamplecode.domain.store.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.domain.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.service.MenuService;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreCreateRequest;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreUpdateRequest;
import com.wearei.finalsamplecode.domain.store.dto.response.*;
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
    private final MenuService menuService;
    private final GroundRepository groundRepository;

    // 가게 생성
    public StoreCreateResponse createStore(StoreCreateRequest request,  AuthUser authUser) {
        User user = User.fromAuthUser(authUser);

        authCheck(authUser);

        Ground ground = groundRepository.findById(request.getGroundId()).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));

        // 구장별로 같은 가게 이름 일 시 예외처리 (다른 구장에서 가게 이름을 같게 만들어도 됨)
        if(storeRepository.existsByStoreNameAndGround(request.getStoreName(), ground)) {
            throw new ApiException(ErrorStatus._STORE_ALREADY_EXISTS);
        }

        Store store = new Store(
                ground,
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
        Ground ground = groundRepository.findById(request.getGroundId()).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));
        Store store = checkStore(storeId);

        authCheck(authUser);

        store.update(ground, request.getStoreName(), request.getOpenedAt(), request.getClosedAt());

        return new StoreUpdateResponse(store.getStoreName(), store.getOpenedAt(), store.getClosedAt());
    }

    // 가게 다건 조회
    @Transactional(readOnly = true)
    public List<StoreGetAllResponse> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreGetAllResponse> storeList = new ArrayList<>();
        for (Store store : stores) {
            StoreGetAllResponse response = new StoreGetAllResponse(store.getStoreName(), store.getOpenedAt(), store.getClosedAt());
            storeList.add(response);
        }
        return storeList;
    }

    // 가게 단건 조회 ( 메뉴도 같이 조회 )
    @Transactional(readOnly = true)
    public StoreGetResponse getStore(Long storeId) {
        Store store = checkStore(storeId);

        List<Menu> menus = menuService.findAllMenus();
        List<CreateMenuResponse> menuList = new ArrayList<>();
        for (Menu menu : menus){
            CreateMenuResponse response = new CreateMenuResponse( store.getStoreName() ,menu.getMenuName());
            menuList.add(response);
        }

        return new StoreGetResponse(store.getStoreName(), menuList);
    }

    // 통합검색 (가게 + 메뉴)
    @Transactional(readOnly = true)
    public List<StoreIntegratedResponse> searchStoresOrMenus(String storeName, String menuName) {
        List<Store> stores = storeRepository.searchStoresOrMenu(storeName, menuName);
        List<StoreIntegratedResponse> storeList = new ArrayList<>();
        for (Store store : stores) {
            StoreIntegratedResponse response = new StoreIntegratedResponse(store.getStoreName(), store.getOpenedAt(), store.getClosedAt(), store.isDeleted());
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
    public User checkUser(Long userId){
        return userRepository.findById(userId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }

    // 가게 확인
    public Store checkStore(Long storeId){
        Store store =  storeRepository.findById(storeId).orElseThrow(()
        -> new ApiException(ErrorStatus._NOT_FOUND_STORE));

        if(store.isDeleted()){
            throw new ApiException(ErrorStatus._NOT_FOUND_STORE);
        }

        return store;
    }

    // 가게 사장님 권한 확인
    public void authCheck(AuthUser authUser) {
        if (!authUser.getUserRole().equals(UserRole.ROLE_OWNER)){
            throw new ApiException(ErrorStatus._BAD_REQUEST_STORE);
        }
    }
}
