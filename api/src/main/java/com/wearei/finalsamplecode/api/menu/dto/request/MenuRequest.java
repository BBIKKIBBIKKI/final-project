package com.wearei.finalsamplecode.api.menu.dto.request;

import jakarta.validation.constraints.NotNull;
import static com.wearei.finalsamplecode.api.menu.dto.request.MenuRequest.*;

public sealed interface MenuRequest permits Create, Update, Delete {

    record Create(
            @NotNull Long storeId,
            @NotNull String menuName,
            @NotNull Long price,
            @NotNull Long inventory
    ) implements MenuRequest {}

    record Update(
            Long storeId,
            String menuName,
            Long price
    ) implements MenuRequest {}

    record Delete(
            Long storeId
    ) implements MenuRequest {}
}
