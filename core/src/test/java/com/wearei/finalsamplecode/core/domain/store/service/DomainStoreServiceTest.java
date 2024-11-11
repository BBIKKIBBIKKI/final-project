package com.wearei.finalsamplecode.core.domain.store.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Transactional
@SpringBootTest
class DomainStoreServiceTest {
    @Autowired
    private DomainStoreService domainStoreService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GroundRepository groundRepository;

    // 가게 생성 3개
    @Test
    void 가게_정상_생성() {
        // given
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);

        Team team = new Team("두산", "a", "s","s","s");
        teamRepository.save(team);
        Ground ground = new Ground("잠실구장", "서울", "010010", "a", team);
        groundRepository.save(ground);

        StoreCreateRequest request = new StoreCreateRequest(ground.getId(), "피자가게", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10));

        // when
        StoreCreateResponse response = storeService.createStore(request, authUser);

        // then
        assertNotNull(response);
        assertEquals("피자가게", response.getStoreName());
    }

    @Test
    void 동일한_가게_생성시_예외처리() {
        // given
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);

        Team team = new Team("두산", "a", "s","s","s");
        teamRepository.save(team);
        Ground ground = new Ground("잠실구장", "서울", "010010", "a", team);
        groundRepository.save(ground);

        StoreCreateRequest request = new StoreCreateRequest(ground.getId(), "피자가게", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10));
        StoreCreateRequest request2 = new StoreCreateRequest(ground.getId(), "피자가게", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10));

        storeService.createStore(request, authUser);

        // when & then
        Exception exception = assertThrows(ApiException.class, () -> {
            storeService.createStore(request2, authUser);
        });

        assertEquals(ErrorStatus._STORE_ALREADY_EXISTS.getMessage(), exception.getMessage());
    }

    @Test
    void 구장_없을_시_예외처리() {
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);

        StoreCreateRequest request = new StoreCreateRequest(-1L, "피자가게", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10));

        Exception exception = assertThrows(ApiException.class, () -> {
            storeService.createStore(request, authUser);
        });
        assertEquals(ErrorStatus._NOT_FOUND_GROUND.getMessage(), exception.getMessage());
    }

    // 가게 수정
    @Test
    void 가게_수정_성공() {
        // given
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);

        Team team = new Team("두산", "a", "s","s","s");
        teamRepository.save(team);
        Ground ground = new Ground("잠실구장", "서울", "010010", "a", team);
        groundRepository.save(ground);

        Store store = new Store(ground,"피자", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user);
        storeRepository.save(store);

        StoreUpdateRequest request = new StoreUpdateRequest(ground.getId(), "수정 피자가게", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10));

        // when
        StoreUpdateResponse response = storeService.updateStore(request, authUser, store.getId());

        // then
        assertNotNull(response);
    }
}