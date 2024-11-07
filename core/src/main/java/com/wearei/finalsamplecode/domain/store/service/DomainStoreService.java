package com.wearei.finalsamplecode.domain.store.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.domain.menu.service.DomainMenuService;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional
public class DomainStoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final DomainMenuService domainMenuService;
    private final GroundRepository groundRepository;

    // 가게 생성
    public Store createStore(Long groundId, String storeName, LocalTime openedAt, LocalTime closedAt, AuthUser authUser) {
        User user = User.fromAuthUser(authUser);

        authCheck(authUser);

        Ground ground = groundRepository.findById(groundId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));

        // 구장별로 같은 가게 이름 일 시 예외처리 (다른 구장에서 가게 이름을 같게 만들어도 됨)
        if(storeRepository.existsByStoreNameAndGround(storeName, ground)) {
            throw new ApiException(ErrorStatus._STORE_ALREADY_EXISTS);
        }

        return storeRepository.save(new Store(
                ground,
                storeName,
                openedAt,
                closedAt,
                user
        ));
    }

    // 가게 수정
    public Store updateStore(Long groundId, String storeName, LocalTime openedAt, LocalTime closedAt, AuthUser authUser, Long storeId) {
        Ground ground = groundRepository.findById(groundId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));
        Store store = checkStore(storeId);

        authCheck(authUser);

        store.update(ground, storeName, openedAt, closedAt);

        return store;
    }

    // 가게 삭제
    public void deleteStore(Long storeId, AuthUser authUser) {

        Store store = checkStore(storeId);

        authCheck(authUser);

        store.softDelete();
        storeRepository.save(store);

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
