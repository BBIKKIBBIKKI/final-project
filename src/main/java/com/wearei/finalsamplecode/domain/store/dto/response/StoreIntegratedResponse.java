package com.wearei.finalsamplecode.domain.store.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class StoreIntegratedResponse {
    private final String storeName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;
    private final Boolean isDeleted;
}
