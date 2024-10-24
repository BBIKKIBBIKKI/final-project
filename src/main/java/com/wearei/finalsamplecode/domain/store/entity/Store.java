package com.wearei.finalsamplecode.domain.store.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name="id", column = @Column(name="store_id"))
@Table(name="Stores")
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

    public Store(String storeName, LocalTime openedAt, LocalTime closedAt, User user) {
        this.storeName = storeName;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.user = user;
    }

    public void update(String storeName, LocalTime openedAt, LocalTime closedAt) {
        this.storeName = storeName;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }

    public void softDelete() {
        isDeleted = true;
    }
}
