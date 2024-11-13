package com.wearei.finalsamplecode.api.order.dto.request;

import com.wearei.finalsamplecode.core.domain.order.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import static com.wearei.finalsamplecode.api.order.dto.request.OrderRequest.*;

public sealed interface OrderRequest permits Create, Update, UpdateOrderStatus {

    record Create(
            @NotNull Long storeId,
            @NotNull Long menuId,
            @NotNull Long quantity
    ) implements OrderRequest {}

    record Update (
            Long storeId,
            Long menuId,
            Long quantity
    ) implements OrderRequest {}

    record UpdateOrderStatus (
            OrderStatus orderStatus
    ) implements OrderRequest {}
}
