//package com.wearei.finalsamplecode.api.store.service;
//
//import com.wearei.finalsamplecode.api.store.DefaultStoreService;
//import com.wearei.finalsamplecode.api.store.dto.response.StoreResponse;
//import com.wearei.finalsamplecode.common.enums.UserRole;
//import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
//import com.wearei.finalsamplecode.core.domain.ground.repository.GroundRepository;
//import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
//import com.wearei.finalsamplecode.core.domain.menu.repository.MenuRepository;
//import com.wearei.finalsamplecode.core.domain.store.entity.Store;
//import com.wearei.finalsamplecode.core.domain.store.repository.StoreRepository;
//import com.wearei.finalsamplecode.core.domain.team.entity.Team;
//import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
//import com.wearei.finalsamplecode.core.domain.user.entity.User;
//import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalTime;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Transactional
//@SpringBootTest
//class DefaultStoreServiceTest {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Autowired
//    private DefaultStoreService defaultStoreService;
//    @Autowired
//    private StoreRepository storeRepository;
//    @Autowired
//    private MenuRepository menuRepository;
//    @Autowired
//    private TeamRepository teamRepository;
//    @Autowired
//    private GroundRepository groundRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    private User user;
//    private Ground ground;
//    private Team team;
//
//    @BeforeAll
//    void setUp() {
//        groundRepository.deleteAll();
//        storeRepository.deleteAll();
//        menuRepository.deleteAll();
//        teamRepository.deleteAll();
//        userRepository.deleteAll();
//
//        user = userRepository.save(new User(
//                "unique_testadmin@example.com",
//                "이재희1",
//                "password123!",
//                UserRole.ROLE_OWNER));
//        team = teamRepository.save(new Team("두산_" + UUID.randomUUID(), "a", "s", "s", "s"));
//        ground = groundRepository.save(new Ground("잠실구장", "서울", "010010", "a", team));
//    }
//
//    @Test
//    void 가게_다건_조회() {
//        // given
//        Store store1 = storeRepository.save(new Store(ground, "피자집1", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user));
//        Store store2 = storeRepository.save(new Store(ground, "피자집2", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user));
//
//        // when
//        List<StoreResponse.GetAll> response = defaultStoreService.getAllStores().stream().map(StoreResponse.GetAll::new).toList();
//
//        // then
//        assertNotNull(response);
//        assertEquals(2, response.size());
//        assertEquals("피자집1", response.get(0).storeName());
//    }
//
//    @Test
//    void 가게_단건_조회() {
//        // given
//        Store store = storeRepository.save(new Store(ground, "피자집", LocalTime.now().plusHours(5), LocalTime.now().plusHours(10), user));
//        Menu menu1 = new Menu(store, "메뉴1", 1000L, user);
//        Menu menu2 = new Menu(store, "메뉴2", 2000L, user);
//
//        // when
//        menuRepository.save(menu1);
//        menuRepository.save(menu2);
//
//        entityManager.flush();
//        entityManager.clear();
//
//        StoreResponse.GetWithMenus response = new StoreResponse.GetWithMenus(defaultStoreService.getStore(store.getId()));
//        // then
//        assertNotNull(response);
//        assertEquals("피자집", response.storeName());
//        assertEquals(2, response.menus().size());
//        assertEquals("메뉴1", response.menus().get(0).getMenuName());
//    }
//}
