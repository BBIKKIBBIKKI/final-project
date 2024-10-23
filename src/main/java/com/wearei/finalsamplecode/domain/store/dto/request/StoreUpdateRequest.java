package com.wearei.finalsamplecode.domain.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateRequest {
    private String storeName;
    private LocalTime openedAt;
    private LocalTime closedAt;
}
