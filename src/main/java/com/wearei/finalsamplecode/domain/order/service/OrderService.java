package com.wearei.finalsamplecode.domain.order.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.menu.service.MenuService;
import com.wearei.finalsamplecode.domain.order.dto.request.CreateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.request.UpdateOrderRequest;
import com.wearei.finalsamplecode.domain.order.dto.request.UpdateOrderStatusRequest;
import com.wearei.finalsamplecode.domain.order.dto.response.CreateOrderResponse;
import com.wearei.finalsamplecode.domain.order.dto.response.GetOrderResponse;
import com.wearei.finalsamplecode.domain.order.dto.response.UpdateOrderResponse;
import com.wearei.finalsamplecode.domain.order.dto.response.UpdateOrderStatusResponse;
import com.wearei.finalsamplecode.domain.order.entity.Order;
import com.wearei.finalsamplecode.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.domain.order.event.OrderStatusChangeEvent;
import com.wearei.finalsamplecode.domain.order.repository.OrderRepository;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.store.service.StoreService;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreService storeService;
    private final MenuService menuService;
    private final ApplicationEventPublisher eventPublisher;

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
        orderRepository.save(order);

        return new CreateOrderResponse(
                order.getId(),
                order.getStore().getStoreName(),
                order.getMenu().getMenuName(),
                order.getQuantity(),
                order.getTotalPrice()
        );
    }

    // 주문 수정
     public UpdateOrderResponse updateOrder(Long orderId, UpdateOrderRequest request) {
        // db 조회
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

        return new UpdateOrderResponse(
                order.getId(),
                order.getStore().getStoreName(),
                order.getMenu().getMenuName(),
                order.getQuantity(),
                totalPrice);
    }

    // 주문 상태 수정
    public UpdateOrderStatusResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request, AuthUser authUser) {
        Order order = orderRepository.findByOrderIdOrThrow(orderId);

        storeService.checkStore(order.getStore().getId());

        storeService.authCheck(authUser);

        order.updateStatus(request.getOrderStatus());
//        orderRepository.save(order);

        if (OrderStatus.isComplete(request.getOrderStatus())) {
            String message = String.format("%s 님 조리 완료되었으니 받으러 오세요.", order.getUser().getUsername());
            eventPublisher.publishEvent(new OrderStatusChangeEvent(message));
        }

        return new UpdateOrderStatusResponse(
                order.getOrderStatus()
        );
    }

    // 주문 조회
    @Transactional(readOnly = true)
    public GetOrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findByOrderIdOrThrow(orderId);

        return new GetOrderResponse(
                order.getId(),
                order.getStore().getStoreName(),
                order.getMenu().getMenuName(),
                order.getTotalPrice(),
                order.getQuantity(),
                order.getOrderStatus()
        );
    }

    // 주문 삭제
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findByOrderIdOrThrow(orderId);
        orderRepository.delete(order);
    }

    // 사용자 확인
    private User findUserByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
