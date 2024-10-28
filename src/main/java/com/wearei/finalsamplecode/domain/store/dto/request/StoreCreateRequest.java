package com.wearei.finalsamplecode.domain.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreCreateRequest {
    private Long groundId;
    private String storeName;
    private LocalTime openedAt;
    private LocalTime closedAt;

}
