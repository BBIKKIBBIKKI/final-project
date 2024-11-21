package com.wearei.finalsamplecode.core.domain.store.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomainStoreServiceTest {

    @Mock
    private StoreRepository storeRepository;
    @Mock
    private GroundRepository groundRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DomainStoreService storeService;

    private User user;
    private Ground ground;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User("test123@naver.com","이재희","Abcdefg@123", UserRole.ROLE_OWNER);
        ground = new Ground("잠실구장","서울","02-1555-1555","groundImg",new Team());
    }

    @Test
    void 가게_정상_생성() {
        // given
        String storeName = "피자가게";
        LocalTime openedAt = LocalTime.of(9, 0);
        LocalTime closedAt = LocalTime.of(22, 0);

        when(userRepository.findByIdOrThrow(user.getId())).thenReturn(user);
        when(groundRepository.findById(ground.getId())).thenReturn(Optional.of(ground));
        when(storeRepository.existsByStoreNameAndGround(storeName, ground)).thenReturn(false);
        when(storeRepository.save(any(Store.class))).thenReturn(new Store(ground, storeName, openedAt, closedAt, user));

        // when
        Store store = storeService.createStore(user.getId(), ground.getId(), storeName, openedAt, closedAt);

        // then
        assertNotNull(store);
        assertEquals(storeName, store.getStoreName());
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void 동일한_가게_생성시_예외처리() {
        // given
        String storeName = "피자가게";
        LocalTime openedAt = LocalTime.of(9, 0);
        LocalTime closedAt = LocalTime.of(22, 0);

        when(userRepository.findByIdOrThrow(user.getId())).thenReturn(user);
        when(groundRepository.findById(ground.getId())).thenReturn(Optional.of(ground));
        when(storeRepository.existsByStoreNameAndGround(storeName, ground)).thenReturn(true);

        // when & then
        ApiException exception = assertThrows(ApiException.class,
                () -> storeService.createStore(user.getId(), ground.getId(), storeName, openedAt, closedAt));
        assertEquals(ErrorStatus._STORE_ALREADY_EXISTS.getMessage(), exception.getMessage());
    }

    @Test
    void 구장_없을_시_예외처리() {
        // given
        String storeName = "피자가게";
        LocalTime openedAt = LocalTime.of(9, 0);
        LocalTime closedAt = LocalTime.of(22, 0);

        when(userRepository.findByIdOrThrow(user.getId())).thenReturn(user);
        when(groundRepository.findById(ground.getId())).thenReturn(Optional.empty());

        // when & then
        ApiException exception = assertThrows(ApiException.class,
                () -> storeService.createStore(user.getId(), ground.getId(), storeName, openedAt, closedAt));
        assertEquals(ErrorStatus._NOT_FOUND_GROUND.getMessage(), exception.getMessage());
    }

    @Test
    void 가게_수정_성공() {
        // given
        String updatedStoreName = "수정 피자가게";
        LocalTime updatedOpenedAt = LocalTime.of(10, 0);
        LocalTime updatedClosedAt = LocalTime.of(23, 0);
        Store store = new Store(ground, "피자가게", LocalTime.of(9, 0), LocalTime.of(22, 0), user);

        when(userRepository.findByIdOrThrow(user.getId())).thenReturn(user);
        when(groundRepository.findById(ground.getId())).thenReturn(Optional.of(ground));
        when(storeRepository.findById(store.getId())).thenReturn(Optional.of(store));

        // when
        storeService.updateStore(user.getId(), ground.getId(), updatedStoreName, updatedOpenedAt, updatedClosedAt, store.getId());

        // then
        assertEquals(updatedStoreName, store.getStoreName());
        assertEquals(updatedOpenedAt, store.getOpenedAt());
        assertEquals(updatedClosedAt, store.getClosedAt());
    }
}
