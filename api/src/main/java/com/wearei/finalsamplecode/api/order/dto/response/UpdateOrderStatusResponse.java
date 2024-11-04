package com.wearei.finalsamplecode.api.order.dto.response;

import com.wearei.finalsamplecode.api.order.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateOrderStatusResponse {
    private final OrderStatus orderStatus;
}
