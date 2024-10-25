package com.wearei.finalsamplecode.domain.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    RESERVED("주문예약"),
    COOKING("조리중"),
    COMPLETED("조리 완료");

    private String orderStatus;
}
