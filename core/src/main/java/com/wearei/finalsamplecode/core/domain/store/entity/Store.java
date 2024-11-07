package com.wearei.finalsamplecode.core.domain.store.entity;

import com.wearei.finalsamplecode.core.domain.ground.entity.Ground;
import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.common.entity.BaseEntity;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name="id", column = @Column(name="store_id"))
@Table(name="stores")
public class Store extends BaseEntity {
    @Column(name="store_name", nullable=false)
    private String storeName;

    @Column(name="opened_at", nullable=false)
    private LocalTime openedAt;

    @Column(name="closed_at", nullable=false)
    private LocalTime closedAt;

    @Column(name= "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ground_id", nullable = false)
    private Ground ground;

    public Store(Ground ground, String storeName, LocalTime openedAt, LocalTime closedAt, User user) {
        this.ground = ground;
        this.storeName = storeName;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.user = user;
    }

    public Store(Ground ground, String storeName, LocalTime openedAt, LocalTime closedAt) {
        this.ground = ground;
        this.storeName = storeName;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }

    public Store(String storeName, LocalTime openedAt, LocalTime closedAt) {
        this.storeName = storeName;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }

    public Store(String storeName, List<Menu> menus) {
        this.storeName = storeName;
        this.menus = menus;
    }

    public void update(Ground ground, String storeName, LocalTime openedAt, LocalTime closedAt) {
        this.ground = ground;
        this.storeName = storeName;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }

    public void softDelete() {
        isDeleted = true;
    }
}
