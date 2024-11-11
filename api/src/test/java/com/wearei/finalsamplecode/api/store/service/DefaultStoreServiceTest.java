package com.wearei.finalsamplecode.api.store.service;

import com.wearei.finalsamplecode.api.store.DefaultStoreService;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.security.AuthUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class DefaultStoreServiceTest {

    @Autowired
    private DefaultStoreService defaultStoreService;

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

}