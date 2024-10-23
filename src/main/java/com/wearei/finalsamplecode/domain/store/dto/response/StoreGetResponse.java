package com.wearei.finalsamplecode.domain.store.dto.response;

import com.wearei.finalsamplecode.domain.menu.dto.response.CreateMenuResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StoreGetResponse {
    private final String storeName;
    private final List<CreateMenuResponse> menus;
}
