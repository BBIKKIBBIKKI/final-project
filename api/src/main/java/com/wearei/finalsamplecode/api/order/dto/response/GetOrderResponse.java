package com.wearei.finalsamplecode.api.order.dto.response;

import com.wearei.finalsamplecode.api.order.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetOrderResponse {
    private final Long orderId;
    private final String storeName;
    private final String menuName;
    private final Long quantity;
    private final Long totalPrice;
    private final OrderStatus status;
}
