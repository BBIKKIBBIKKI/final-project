package com.wearei.finalsamplecode.api.order.dto.response;

import com.wearei.finalsamplecode.domain.order.entity.Order;
import com.wearei.finalsamplecode.domain.order.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateOrderStatusResponse {
    private final OrderStatus orderStatus;

    public UpdateOrderStatusResponse(Order order) {
        this.orderStatus = order.getOrderStatus();
    }
}
