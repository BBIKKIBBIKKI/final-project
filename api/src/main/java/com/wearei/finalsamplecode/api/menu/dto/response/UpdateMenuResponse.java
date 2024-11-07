package com.wearei.finalsamplecode.api.menu.dto.response;

import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateMenuResponse {
    private final String menuName;

    public UpdateMenuResponse(Menu menu) {
        this.menuName = menu.getMenuName();
    }
}
