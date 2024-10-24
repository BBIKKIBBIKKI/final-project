package com.wearei.finalsamplecode.domain.order.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.service.MenuService;
import com.wearei.finalsamplecode.domain.order.dto.request.CreateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.request.UpdateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.response.CreateOrderResponse;
import com.wearei.finalsamplecode.domain.order.dto.response.UpdateOrderResponse;
import com.wearei.finalsamplecode.domain.order.entity.Order;
import com.wearei.finalsamplecode.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.domain.order.repository.OrderRepository;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.service.StoreService;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreService storeService;
    private final MenuService menuService;

    // 주문 생성
    public CreateOrderResponse createOrder(CreateOrderRequest request, AuthUser authUser) {
        User orderUser = findUserByUserId(authUser.getUserId());

        Store store = storeService.checkStore(request.getStoreId());

        Menu menu = menuService.checkMenu(request.getMenuId());

        Long totalPrice = menu.getPrice() * request.getQuantity();

        Order order = new Order(
                orderUser,
                OrderStatus.RESERVED,
                store,
                menu,
                request.getQuantity(),
                totalPrice
        );
        Order savedOrder = orderRepository.save(order);

        return new CreateOrderResponse(
                savedOrder.getId(),
                savedOrder.getStore().getStoreName(),
                savedOrder.getMenu().getMenuName(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice()
        );
    }

    // 주문 수정
    public UpdateOrderResponse updateOrder(Long orderId, UpdateOrderRequest request) {
        Order order = orderRepository.findByOrderIdOrThrow(orderId);
        Store store = storeService.checkStore(request.getStoreId());
        Menu menu = menuService.checkMenu(request.getMenuId());

        // 상점 업데이트
        order.updateStore(store);

        // 최종 가격 계산
        Long totalPrice = menu.getPrice() * request.getQuantity();

        // 나중에 stream 으로 변경
        for (Menu storeMenu : store.getMenus()) {
            if (storeMenu.getId().equals(menu.getId())) {
                menuService.checkMenu(storeMenu.getId());
                order.updateMenu(storeMenu);
                order.updateTotalPrice(totalPrice);
            }
        }

        return UpdateOrderResponse.builder()
                .orderId(order.getId())
                .storeName(order.getStore().getStoreName())
                .menuName(order.getMenu().getMenuName())
                .quantity(order.getQuantity())
                .totalPrice(totalPrice)
                .build();
    }

    // 사용자 확인
    private User findUserByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
