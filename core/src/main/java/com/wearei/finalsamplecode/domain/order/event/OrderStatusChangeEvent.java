package com.wearei.finalsamplecode.domain.order.event;

import com.wearei.finalsamplecode.common.event.SQSEvent;

public class OrderStatusChangeEvent extends SQSEvent {
    public OrderStatusChangeEvent(String message) {
        super(message);
    }
}
