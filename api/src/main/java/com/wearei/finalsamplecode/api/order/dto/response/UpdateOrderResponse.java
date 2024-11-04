package com.wearei.finalsamplecode.api.order.dto.response;

@Getter
@RequiredArgsConstructor
public class UpdateOrderResponse {
    private final Long orderId;
    private final String storeName;
    private final String menuName;
    private final Long quantity;
    private final Long totalPrice;
}
