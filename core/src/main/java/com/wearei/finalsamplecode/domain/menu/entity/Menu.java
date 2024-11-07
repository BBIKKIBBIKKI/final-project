package com.wearei.finalsamplecode.domain.menu.entity;

import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.user.entity.User;
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

    public Menu(Store store, String menuName, Long price, User user) {
        this.store = store;
        this.menuName = menuName;
        this.price = price;
        this.user = user;
    }

    public void update(String menuName, Long price) {
        this.menuName = menuName;
        this.price = price;
    }
}
