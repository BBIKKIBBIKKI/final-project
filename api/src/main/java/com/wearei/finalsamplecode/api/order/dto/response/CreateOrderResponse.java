package com.wearei.finalsamplecode.api.order.dto.response;

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
}
