package com.wearei.finalsamplecode.core.domain.order.event;
import com.wearei.finalsamplecode.integration.event.SQSEvent;

public class OrderStatusChangeEvent extends SQSEvent {
    public OrderStatusChangeEvent(String message) {
        super(message);
    }
}
