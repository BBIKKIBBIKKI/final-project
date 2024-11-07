package com.wearei.finalsamplecode.core.domain.order.entity;

import com.wearei.finalsamplecode.core.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.core.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.core.domain.store.entity.Store;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AttributeOverride(name="id", column = @Column(name="order_id"))
@Table(name="orders")
public class Order extends BaseEntity {

    @Column(name = "quantity", nullable = false)
    private Long quantity; // 주문한 수량

    @Column(name = "total_price", nullable = false)
    private Long totalPrice; // 총 주문 금액

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus; // 주문 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 주문한 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; // 주문한 가게

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu; // 주문한 메뉴

    public Order(User orderUser, OrderStatus orderStatus, Store store, Menu menu, Long quantity, Long totalPrice) {
        this.user = orderUser;
        this.orderStatus = orderStatus;
        this.store = store;
        this.menu = menu;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void updateMenu(Menu menu) {
        this.menu = menu;
    }

    public void updateStore(Store store) {
        this.store = store;
    }

    public void updateTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
