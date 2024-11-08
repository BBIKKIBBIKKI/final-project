package com.wearei.finalsamplecode.api.store.dto.response;

import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import java.time.LocalTime;
import java.util.List;

import static com.wearei.finalsamplecode.api.store.dto.response.StoreResponse.*;

public sealed interface StoreResponse permits Create, Update, GetAll, GetWithMenus, Integrated {

    record Create(
            String storeName,
            LocalTime openedAt,
            LocalTime closedAt
    ) implements StoreResponse {
        public Create(Store store){
            this(
                    store.getStoreName(),
                    store.getOpenedAt(),
                    store.getClosedAt()
            );
        }
    }

    record Update(
            String storeName,
            LocalTime openedAt,
            LocalTime closedAt
    ) implements StoreResponse {
        public Update(Store store) {
            this(
                    store.getStoreName(),
                    store.getOpenedAt(),
                    store.getClosedAt()
            );
        }
    }

    record GetAll(
            String storeName,
            LocalTime openedAt,
            LocalTime closedAt
    ) implements StoreResponse {
        public GetAll(Store store) {
            this(
                    store.getStoreName(),
                    store.getOpenedAt(),
                    store.getClosedAt()
            );
        }
    }

    record GetWithMenus(
            String storeName,
            List<Menu> menus
    ) implements StoreResponse {
        public GetWithMenus(Store store){
            this(
                    store.getStoreName(),
                    store.getMenus()
            );
        }
    }

    record Integrated(
            String storeName,
            LocalTime openedAt,
            LocalTime closedAt,
            Boolean isDeleted
    ) implements StoreResponse {
        public Integrated(Store store){
            this(
                    store.getStoreName(),
                    store.getOpenedAt(),
                    store.getClosedAt(),
                    store.isDeleted()
            );
        }
    }
}
