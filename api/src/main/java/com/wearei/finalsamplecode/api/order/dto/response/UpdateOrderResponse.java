package com.wearei.finalsamplecode.api.order.dto.response;

import com.wearei.finalsamplecode.domain.order.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateOrderResponse {
    private final Long orderId;
    private final String storeName;
    private final String menuName;
    private final Long quantity;
    private final Long totalPrice;

    public UpdateOrderResponse(Order order) {
        this.orderId = order.getId();
        this.storeName = order.getStore().getStoreName();
        this.menuName = order.getMenu().getMenuName();
        this.quantity = order.getQuantity();
        this.totalPrice = order.getTotalPrice();
    }
}