package com.wearei.finalsamplecode.api.order.dto.request;

import com.wearei.finalsamplecode.core.domain.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {
    private OrderStatus orderStatus;
}
