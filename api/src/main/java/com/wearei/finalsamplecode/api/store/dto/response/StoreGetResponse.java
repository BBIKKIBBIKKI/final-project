package com.wearei.finalsamplecode.api.store.dto.response;

import com.wearei.finalsamplecode.api.menu.dto.response.CreateMenuResponse;

import java.util.ArrayList;
import java.util.List;

import com.wearei.finalsamplecode.domain.store.entity.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreGetResponse {
    private final String storeName;
    private final List<CreateMenuResponse> menus;

    public StoreGetResponse(Store stores) {
        this.storeName = stores.getStoreName();
        this.menus = new ArrayList<>();
    }
}
