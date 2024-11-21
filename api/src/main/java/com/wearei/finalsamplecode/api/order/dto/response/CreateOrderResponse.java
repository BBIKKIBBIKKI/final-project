package com.wearei.finalsamplecode.api.order.dto.response;

import com.wearei.finalsamplecode.core.domain.order.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderResponse {
    private final Long orderId;
    private final String storeName;
    private final String menuName;
    private final Long quantity;
    private final Long totalPrice;

    public CreateOrderResponse(Order order) {
        this.orderId = order.getId();
        this.storeName = order.getStore().getStoreName();
        this.menuName = order.getMenu().getMenuName();
        this.quantity = order.getQuantity();
        this.totalPrice = order.getTotalPrice();
    }
}
