package com.wearei.finalsamplecode.core.domain.order.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.menu.service.DomainMenuService;
import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import com.wearei.finalsamplecode.core.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.core.domain.order.event.OrderStatusChangeEvent;
import com.wearei.finalsamplecode.core.domain.order.repository.OrderRepository;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.store.service.DomainStoreService;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DomainStoreService domainStoreService;
    private final DomainMenuService domainMenuService;
    private final ApplicationEventPublisher eventPublisher;

    // 주문 생성
    public Order createOrder(Long userId, Long storeId, Long menuId, Long quantity) {
        User orderUser = findUserByUserId(userId);

        Store store = domainStoreService.checkStore(storeId);

        Menu menu = domainMenuService.checkMenu(menuId);

        Long totalPrice = menu.getPrice() * quantity;

        return orderRepository.save( new Order(
                orderUser,
                OrderStatus.RESERVED,
                store,
                menu,
                quantity,
                totalPrice
        ));
    }

    // 주문 수정
     public Order updateOrder(Long orderId, Long storeId, Long menuId, Long quantity) {
        // db 조회
        Order order = orderRepository.findByOrderIdOrThrow(orderId);
        Store store = domainStoreService.checkStore(storeId);
        Menu menu = domainMenuService.checkMenu(menuId);

        // 상점 업데이트
        order.updateStore(store);

        // 최종 가격 계산
        Long totalPrice = menu.getPrice() * quantity;

        for (Menu storeMenu : store.getMenus()) {
            if (storeMenu.getId().equals(menu.getId())) {
                domainMenuService.checkMenu(storeMenu.getId());
                order.updateMenu(storeMenu);
                order.updateTotalPrice(totalPrice);
            }
        }

        return order;
    }

    // 주문 상태 수정
    public Order updateOrderStatus(Long userId, Long orderId, OrderStatus orderStatus) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_OWNER)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

        Order order = orderRepository.findByOrderIdOrThrow(orderId);

        domainStoreService.checkStore(order.getStore().getId());

        order.updateStatus(orderStatus);

        if (OrderStatus.isComplete(orderStatus)) {
            String message = String.format("%s 님 조리 완료되었으니 받으러 오세요.", order.getUser().getUsername());
            eventPublisher.publishEvent(new OrderStatusChangeEvent(message));
        }

        return order;
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
