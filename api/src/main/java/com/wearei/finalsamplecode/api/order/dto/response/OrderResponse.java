package com.wearei.finalsamplecode.api.order.dto.response;

import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import com.wearei.finalsamplecode.core.domain.order.enums.OrderStatus;
import static com.wearei.finalsamplecode.api.order.dto.response.OrderResponse.*;

public sealed interface OrderResponse permits Create, Update, UpdateOrderStatus, GetOrder {

    record Create (
            Long orderId,
            String storeName,
            String menuName,
            Long quantity,
            Long totalPrice
    ) implements OrderResponse {
        public Create (Order order) {
            this (
                    order.getId(),
                    order.getStore().getStoreName(),
                    order.getMenu().getMenuName(),
                    order.getQuantity(),
                    order.getTotalPrice()
            );
        }
    }

    record Update (
            Long orderId,
            String storeName,
            String menuName,
            Long quantity,
            Long totalPrice
    ) implements OrderResponse {
        public Update (Order order) {
            this (
                    order.getId(),
                    order.getStore().getStoreName(),
                    order.getMenu().getMenuName(),
                    order.getQuantity(),
                    order.getTotalPrice()
            );
        }
    }

    record UpdateOrderStatus (
            OrderStatus orderStatus
    ) implements OrderResponse {
        public UpdateOrderStatus (Order order) {
            this (
                    order.getOrderStatus()
            );
        }
    }

    record GetOrder(
            Long orderId,
            String storeName,
            String menuName,
            Long quantity,
            Long totalPrice,
            OrderStatus orderStatus
    ) implements OrderResponse {
        public GetOrder(Order order){
            this (
                    order.getId(),
                    order.getStore().getStoreName(),
                    order.getMenu().getMenuName(),
                    order.getQuantity(),
                    order.getTotalPrice(),
                    order.getOrderStatus()
            );
        }
    }
}
