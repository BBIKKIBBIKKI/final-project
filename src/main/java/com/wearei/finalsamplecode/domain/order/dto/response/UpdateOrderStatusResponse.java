package com.wearei.finalsamplecode.domain.order.dto.response;

import com.wearei.finalsamplecode.domain.order.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateOrderStatusResponse {
    private final OrderStatus orderStatus;
}
