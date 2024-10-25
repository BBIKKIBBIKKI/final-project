package com.wearei.finalsamplecode.domain.store.service;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.domain.menu.dto.response.CreateMenuResponse;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.repository.MenuRepository;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreCreateRequest;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreUpdateRequest;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreCreateResponse;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreGetAllResponse;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreGetResponse;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreUpdateResponse;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(StoreService.class)
class StoreServiceTest {
    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private GroundRepository groundRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MenuRepository menuRepository;

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

    @Test
    void 가게_다건_조회() {
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);

        Team team = new Team("두산", "a", "s","s","s");
        teamRepository.save(team);

        Ground ground = new Ground("잠실구장", "서울", "010010", "a", team);
        groundRepository.save(ground);

        Store store = new Store(ground,"피자", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user);
        storeRepository.save(store);

        Store store1 = new Store(ground,"피자", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user);
        storeRepository.save(store1);

        List<StoreGetAllResponse> response = storeService.getAllStores();

        assertNotNull(response);

    }

    @Test
    void 가게_단건_조회_성공() {
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);

        Team team = new Team("두산", "a", "s","s","s");
        teamRepository.save(team);

        Ground ground = new Ground("잠실구장", "서울", "010010", "a", team);
        groundRepository.save(ground);

        Store store = new Store(ground,"피자", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user);
        storeRepository.save(store);

        Menu menu = new Menu(store, "메뉴1", 1000L, user);
        Menu menu2 = new Menu(store, "메뉴2", 1000L, user);
        menuRepository.save(menu);
        menuRepository.save(menu2);

        StoreGetResponse response = storeService.getStore(store.getId());

        assertNotNull(response);
    }

    @Test
    void checkUser() {
    }

    @Test
    void checkStore() {
    }

    @Test
    void authCheck() {
    }
}