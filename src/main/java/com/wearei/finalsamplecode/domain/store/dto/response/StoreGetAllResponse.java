package com.wearei.finalsamplecode.domain.store.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class StoreGetAllResponse {
    private final String StoreName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;
}
