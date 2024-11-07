package com.wearei.finalsamplecode.api.store.dto.response;

import java.time.LocalTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreIntegratedResponse {
    private final String storeName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;
    private final Boolean isDeleted;
}