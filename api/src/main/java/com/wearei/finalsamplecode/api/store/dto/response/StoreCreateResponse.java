package com.wearei.finalsamplecode.api.store.dto.response;

import java.time.LocalTime;

import com.wearei.finalsamplecode.domain.store.entity.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreCreateResponse {
    private final String storeName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;

    public StoreCreateResponse(Store store) {
        this.storeName = store.getStoreName();
        this.openedAt = store.getOpenedAt();
        this.closedAt = store.getClosedAt();
    }
}