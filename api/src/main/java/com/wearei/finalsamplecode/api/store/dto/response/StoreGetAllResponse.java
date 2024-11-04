package com.wearei.finalsamplecode.api.store.dto.response;

import java.time.LocalTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreGetAllResponse {
    private final String StoreName;
    private final LocalTime openedAt;
    private final LocalTime closedAt;
}
