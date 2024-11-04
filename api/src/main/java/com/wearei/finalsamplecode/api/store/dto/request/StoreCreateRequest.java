package com.wearei.finalsamplecode.api.store.dto.request;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreCreateRequest {
    private Long groundId;
    private String storeName;
    private LocalTime openedAt;
    private LocalTime closedAt;
}
