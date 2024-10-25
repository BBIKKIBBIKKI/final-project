package com.wearei.finalsamplecode.domain.store.service;

import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.domain.store.dto.request.StoreCreateRequest;
import com.wearei.finalsamplecode.domain.store.dto.response.StoreCreateResponse;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;

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

    @Test
    void 가게_정상_생성() {
        // given
        AuthUser authUser = new AuthUser(1L, "gusrnr5153@naver.com", UserRole.ROLE_OWNER);
        User user = User.fromAuthUser(authUser);
        MultipartFile file = new MockMultipartFile(
                "file",
                "testFile.txt",
                "text/plain",
                "This is the file content".getBytes()
        );

        Team team = new Team("두산", "a", "s","s","s");
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
    void updateStore() {
    }

    @Test
    void getAllStores() {
    }

    @Test
    void getStore() {
    }

    @Test
    void searchStoresOrMenus() {
    }

    @Test
    void deleteStore() {
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