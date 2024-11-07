package com.wearei.finalsamplecode.api.menu.dto.response;

import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMenuResponse {
    private final String storeName;
    private final String menuName;

    public CreateMenuResponse(Menu menu) {
        this.storeName = menu.getStore().getStoreName();
        this.menuName = menu.getMenuName();
    }
}
