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

    // 주문 수정
    public UpdateOrderResponse updateOrder(Long orderId, UpdateOrderRequest request) {
        Order order = orderRepository.findByOrderIdOrThrow(orderId);

        // 가게 조회 및 수정
        if (Objects.nonNull(request.getStoreId())) {
            Store store = storeService.checkStore(request.getStoreId());
            order.updateStore(store);
            {
                // 가게에 속한 메뉴 조회 및 수정 + 해당 메뉴 갯수에 따른 가격 수정
                if (Objects.nonNull(request.getMenuId())) {
                    for (Menu menu : store.getMenus()) {
                        if (menu.getId().equals(request.getMenuId())) {
                            menuService.checkMenu(menu.getId());
                            order.updateMenu(menu);
                            Long totalPrice = menu.getPrice() * request.getQuantity();
                            order.updateTotalPrice(totalPrice);
                            return new UpdateOrderResponse(order.getId(), order.getStore().getStoreName(), order.getMenu().getMenuName(), order.getQuantity(), totalPrice);
                        }
                    }
                    throw new ApiException(ErrorStatus._NOT_FOUND_MENU);
                }
            }
        }
        throw new ApiException(ErrorStatus._NOT_FOUND_ORDER_MENU_LIST);
    }

//    // 주문 수락
//    public OrderStatusResponse approveOrder()



    // 사용자 확인
    private User findUserByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));
    }
}
