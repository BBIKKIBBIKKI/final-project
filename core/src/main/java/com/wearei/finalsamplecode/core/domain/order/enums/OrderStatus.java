package com.wearei.finalsamplecode.core.domain.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    RESERVED("주문예약"),
    COOKING("조리중"),
    COMPLETED("조리 완료");

    private final String orderStatus;

    public static boolean isComplete(OrderStatus status) {
        return status.equals(OrderStatus.COMPLETED);
    }
}
