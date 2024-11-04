package com.wearei.finalsamplecode.api.store.dto.request;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateRequest {
    private Long groundId;
    private String storeName;
    private LocalTime openedAt;
    private LocalTime closedAt;
}
