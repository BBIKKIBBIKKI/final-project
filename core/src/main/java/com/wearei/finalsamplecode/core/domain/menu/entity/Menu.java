package com.wearei.finalsamplecode.core.domain.menu.entity;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.exception.ApiException;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name="id", column = @Column(name="menu_id"))
@Table(name = "menus")
public class Menu extends BaseEntity {
    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name="price", nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long inventory;

    public Menu(Store store, String menuName, Long price, User user, Long inventory) {
        this.store = store;
        this.menuName = menuName;
        this.price = price;
        this.user = user;
        this.inventory = inventory;
    }

    public void update(String menuName, Long price) {
        this.menuName = menuName;
        this.price = price;
    }

    // 분산락 관련
    public void decreaseStock(Long quantity) {
        if (this.inventory < quantity) {
            throw new ApiException(ErrorStatus._INSUFFICIENT_INVENTORY);
        }
        this.inventory -= quantity;
    }
}
