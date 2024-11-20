package com.wearei.finalsamplecode.core.domain.order.service;

import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.TestApplicationConfig;
import com.wearei.finalsamplecode.core.domain.lock.service.StockService;
import com.wearei.finalsamplecode.core.domain.menu.service.DomainMenuService;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = TestApplicationConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DomainOrderLockTest {

    @Autowired
    private DomainOrderService domainOrderService;
    @Autowired
    private DomainMenuService domainMenuService;
    @Autowired
    private DomainStoreService domainStoreService;
    @Autowired
    private StockService stockService;
    @Autowired
    private UserRepository userRepository;

    private static final Long USER_ID = 172L;
    private static final Long MENU_ID = 56L;
    private static final Long STORE_ID = 68L;
    private Long menuId;
    private String stockKey;
    private static final long INVENTORY = 100;
    private static final long PRICE = 14000;
    private static final String MENU_NAME = "한치1";

    @BeforeEach
    void setUp() {

        // Test 준비: Menu와 Stock 설정
//        User user = new User(USER_ID, UserRole.ROLE_OWNER); // USER_ID 지정
        User user = new User(USER_ID, "leejaehee011@google.com", "leejaehee011", "Leejaehee1!", UserRole.ROLE_OWNER);
        userRepository.save(user);

        // MENU_ID를 직접 설정하여 Menu 생성
        menuId = MENU_ID;
        domainMenuService.createMenu(USER_ID, STORE_ID, MENU_NAME, PRICE, INVENTORY); // 메뉴 생성
        stockKey = stockService.keyResolver("menu", menuId.toString());
        stockService.setStock(stockKey, (int) INVENTORY);
    }

    @Test
    @Order(1)
    void 메뉴_재고_초기화_확인() {
        long currentStock = stockService.currentStock(stockKey);
        assertEquals(INVENTORY, currentStock);
    }

    @Test
    @Order(2)
    void 재고_감소_테스트() {
        int decreaseAmount = 2;
        stockService.decrease(stockKey, decreaseAmount);
        int currentStock = stockService.currentStock(stockKey);
        assertEquals(INVENTORY - decreaseAmount, currentStock);
    }

    @Test
    @Order(3)
    void 락O_100명_동시_2개씩_주문_테스트() throws InterruptedException {
        int people = 100;
        long orderQuantity = 2;
        long expectedRemainingStock = Math.max(INVENTORY - (people * orderQuantity), 0);
        CountDownLatch latch = new CountDownLatch(people);

        var workers = Stream
                .generate(() -> new Thread(new OrderWorker(stockKey, (int) orderQuantity, latch)))
                .limit(people)
                .collect(Collectors.toList());
        workers.forEach(Thread::start);
        System.out.println("여기까진 나옴");
        latch.await();
        System.out.println("여기서부터 안됨");
        workers.stream().filter(Thread::isAlive).forEach(Thread::interrupt);

        long currentStock = stockService.currentStock(stockKey);
        assertEquals(expectedRemainingStock, currentStock);
    }

    private class OrderWorker implements Runnable {
        private final String stockKey;
        private final int quantity;
        private final CountDownLatch latch;

        public OrderWorker(String stockKey, int quantity, CountDownLatch latch) {
            this.stockKey = stockKey;
            this.quantity = quantity;
            this.latch = latch;
        }


        @Override
        public void run() {
            try{
                domainMenuService.decreaseStockWithLock(MENU_ID, quantity);
            } catch (Exception e) {
                throw e;
            } finally {
                latch.countDown();
            }
        }
    }

    @Test
    @Order(4)
    void 락X_100명_동시_2개씩_구매_테스트() throws InterruptedException {
        int people = 100;
        long orderQuantity = 2;
        long expectedRemainingStock = Math.max(INVENTORY - (people * orderQuantity), 0);
        CountDownLatch latch = new CountDownLatch(people);

        var workers = Stream.generate(() -> new Thread(new NoLockOrderWorker(stockKey, (int) orderQuantity, latch)))
                .limit(people)
                .collect(Collectors.toList());
        workers.forEach(Thread::start);
        latch.await();

        long currentStock = stockService.currentStock(stockKey);
        assertNotEquals(expectedRemainingStock, currentStock);
    }

    private class NoLockOrderWorker implements Runnable {
        private final String stockKey;
        private final int quantity;
        private final CountDownLatch latch;

        public NoLockOrderWorker(String stockKey, int quantity, CountDownLatch latch) {
            this.stockKey = stockKey;
            this.quantity = quantity;
            this.latch = latch;
        }

        @Override
        public void run() {
            domainMenuService.decreaseStockWithoutLock(MENU_ID, quantity);
            latch.countDown();
        }
    }

    private void setFieldUsingReflection(Object obj, String fieldName, Object value) throws Exception {
        Field field;
        Class<?> clazz = obj.getClass();

        while (true) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
                if (clazz == null) throw new NoSuchFieldException("Field not found: " + fieldName);
            }
        }

        field.setAccessible(true);
        field.set(obj, value);
    }
}
