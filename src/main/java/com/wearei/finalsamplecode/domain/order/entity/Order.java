package com.wearei.finalsamplecode.domain.order.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.domain.menu.entity.Menu;
import com.wearei.finalsamplecode.domain.order.enums.OrderStatus;
import com.wearei.finalsamplecode.domain.store.entity.Store;
import com.wearei.finalsamplecode.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 주문 생성 시간

    @Column(name = "accepted_at")
    private LocalDateTime acceptedAt; // 사장님이 주문을 수락한 시간

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // 주문 수정 시간

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

    public Order(User orderUser, Store store, Menu menu, Long quantity, Long totalPrice) {
        this.user = orderUser;
        this.store = store;
        this.menu = menu;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
