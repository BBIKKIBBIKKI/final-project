package com.wearei.finalsamplecode.api.store.dto.response;

import java.time.LocalTime;
import java.util.List;

import com.wearei.finalsamplecode.domain.store.entity.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreGetAllResponse {
    private final String storeName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;

    public StoreGetAllResponse(Store store) {
        this.storeName = store.getStoreName();
        this.openedAt = store.getOpenedAt();
        this.closedAt = store.getClosedAt();
    }
}
