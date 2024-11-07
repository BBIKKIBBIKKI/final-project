package com.wearei.finalsamplecode.core.domain.store.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
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
    private final GroundRepository groundRepository;

    // 가게 생성
    public Store createStore(Long userId, Long groundId, String storeName, LocalTime openedAt, LocalTime closedAt) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_OWNER)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

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
    public Store updateStore(Long userId, Long groundId, String storeName, LocalTime openedAt, LocalTime closedAt, Long storeId) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_OWNER)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

        Ground ground = groundRepository.findById(groundId).orElseThrow(()
                -> new ApiException(ErrorStatus._NOT_FOUND_GROUND));
        Store store = checkStore(storeId);

        store.update(ground, storeName, openedAt, closedAt);

        return store;
    }

    // 가게 삭제
    public void deleteStore(Long userId, Long storeId) {
        User user = userRepository.findByIdOrThrow(userId);

        Store store = checkStore(storeId);

        store.softDelete();
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
}
