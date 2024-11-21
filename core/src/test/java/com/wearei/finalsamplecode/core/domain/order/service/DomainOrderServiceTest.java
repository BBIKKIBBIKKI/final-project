package com.wearei.finalsamplecode.core.domain.order.service;

import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.lock.service.StockService;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.service.DomainMenuService;
import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import com.wearei.finalsamplecode.core.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.core.domain.order.repository.OrderRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DomainOrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DomainStoreService domainStoreService;
    @Mock
    private DomainMenuService domainMenuService;
    @Mock
    private StockService stockService; // 추가
    @InjectMocks
    private DomainOrderService domainOrderService;

    public static final long INVENTORY = 100;
    public static final String STOCK_KEY = "menu:1"; // 재고 키 상수 선언 및 초기화

    @BeforeEach
    void setUp() {
        // 각 테스트 시작 전에 재고를 초기화
        stockService.setStock(STOCK_KEY, (int) INVENTORY);
        int initialStock = stockService.currentStock(STOCK_KEY);
        System.out.println("초기 재고 설정 완료, 현재 재고: " + initialStock + "개");
    }

    @Test
    public void 주문_정상_생성() throws Exception {
        // given
        Long userId = 1L;
        Long storeId = 1L;
        Long quantity = 2L;
        Long price = 1500L;
        Long totalPrice = price * quantity;

        User user = new User(userId, UserRole.ROLE_OWNER);
        Store store = new Store(storeId);
        Menu menu = new Menu(store, "Original 메뉴", 1000L, user,100L);
        setFieldUsingReflection(menu, "id", 1L);

        Order order = new Order(user, OrderStatus.RESERVED, store, menu, quantity, totalPrice);

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(domainStoreService.checkStore(anyLong())).willReturn(store);
        given(domainMenuService.checkMenu(anyLong())).willReturn(menu);
        given(orderRepository.save(any())).willReturn(order);

        Order createdOrder = domainOrderService.createOrder(user.getId(),store.getId(),menu.getId(),order.getQuantity());

        assertNotNull(createdOrder);
    }

    @Test
    public void 주문_정상_수정() throws Exception {
        // given
        Long userId = 1L;
        Long storeId = 1L;
        Long quantity = 2L;
        Long price = 1500L;
        Long totalPrice = price * quantity;

        User user = new User(userId, UserRole.ROLE_OWNER);
        Store store = new Store(storeId);
        Menu menu = new Menu(store, "Original 메뉴", 1000L, user,100L);
        setFieldUsingReflection(menu, "id", 1L);

        Order order = new Order(user, OrderStatus.RESERVED, store, menu, quantity, totalPrice);
        setFieldUsingReflection(order, "id", 2L);

        given(orderRepository.findByOrderIdOrThrow(anyLong())).willReturn(order);
        given(domainStoreService.checkStore(anyLong())).willReturn(store);
        given(domainMenuService.checkMenu(anyLong())).willReturn(menu);

        Order updateOrder = domainOrderService.updateOrder(user.getId(),store.getId(),menu.getId(),order.getQuantity());

        assertNotNull(updateOrder);
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