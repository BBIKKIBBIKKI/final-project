package com.wearei.finalsamplecode.domain.order.dto.response;

import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class UpdateOrderResponse {
    private final Long orderId;
    private final String storeName;
    private final String menuName;
    private final Long quantity;
    private final Long totalPrice;
}
