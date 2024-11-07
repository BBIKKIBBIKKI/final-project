package com.wearei.finalsamplecode.api.store.dto.response;

import java.time.LocalTime;

import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreUpdateResponse {
    private final String storeName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;

    public StoreUpdateResponse(Store store) {
        this.storeName = store.getStoreName();
        this.openedAt = store.getOpenedAt();
        this.closedAt = store.getClosedAt();
    }
}
