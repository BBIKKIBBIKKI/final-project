package com.wearei.finalsamplecode.api.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {
    private Long storeId;
    private Long menuId;
    private Long quantity;
}
