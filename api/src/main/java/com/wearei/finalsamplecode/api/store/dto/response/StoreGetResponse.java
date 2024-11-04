package com.wearei.finalsamplecode.api.store.dto.response;

import com.wearei.finalsamplecode.api.menu.dto.response.CreateMenuResponse;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreGetResponse {
    private final String storeName;
    private final List<CreateMenuResponse> menus;
}
