package com.wearei.finalsamplecode.api.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import static com.wearei.finalsamplecode.api.store.dto.request.StoreRequest.*;

public sealed interface StoreRequest permits Create, Update {

    record Create(
            @NotNull Long groundId,
            @NotBlank String storeName,
            LocalTime openedAt,
            LocalTime closedAt
    ) implements StoreRequest {}

    record Update(
            Long groundId,
            String storeName,
            LocalTime openedAt,
            LocalTime closedAt
    ) implements StoreRequest {}
}
