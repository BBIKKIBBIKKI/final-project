package com.wearei.finalsamplecode.domain.order.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.service.MenuService;
import com.wearei.finalsamplecode.domain.order.dto.request.CreateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.response.CreateOrderResponse;
import com.wearei.finalsamplecode.domain.order.entity.Order;
import com.wearei.finalsamplecode.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.domain.order.repository.OrderRepository;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.repository.StoreRepository;
import com.wearei.finalsamplecode.domain.store.service.StoreService;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreService storeService;
    private final MenuService menuService;

    // 주문 생성
    public CreateOrderResponse createOrder(CreateOrderRequest request, AuthUser authUser) {
        User orderUser = findUserByUserId(authUser.getId());

        Store store = storeService.checkStore(request.getStoreId());

        Menu menu = menuService.checkMenu(request.getMenuId());

        Long totalPrice = menu.getPrice() * request.getQuantity();

        Order order = new Order(
                orderUser,
                store,
                menu,
                request.getQuantity(),
                totalPrice
        );
        Order savedOrder = orderRepository.save(order);

        order.updateStatus(OrderStatus.RESERVED);

        return new CreateOrderResponse(
                savedOrder.getId(),
                savedOrder.getStore().getStoreName(),
                savedOrder.getMenu().getMenuName(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice()
        );
    }

    // 주문 조회



    // 사용자 확인
    private User findUserByUserId(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
