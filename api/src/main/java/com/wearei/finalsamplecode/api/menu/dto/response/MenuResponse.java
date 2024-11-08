package com.wearei.finalsamplecode.api.menu.dto.response;

import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import static com.wearei.finalsamplecode.api.menu.dto.response.MenuResponse.*;

public sealed interface MenuResponse permits Create, Update {

    record Create(
            String storeName,
            String menuName
    ) implements MenuResponse {
        public Create(Menu menu){
            this (
                    menu.getStore().getStoreName(),
                    menu.getMenuName()
            );
        }
    }

    record Update(
            String menuName
    ) implements MenuResponse {
        public Update(Menu menu){
            this (
                    menu.getMenuName()
            );
        }
    }
}
