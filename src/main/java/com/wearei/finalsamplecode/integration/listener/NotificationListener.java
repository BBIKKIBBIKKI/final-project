package com.wearei.finalsamplecode.integration.listener;

import com.wearei.finalsamplecode.common.event.SQSEvent;
import com.wearei.finalsamplecode.integration.sqs.SQSSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final SQSSender sqsSender;

    @EventListener(SQSEvent.class)
    public void send(SQSEvent event) {
        sqsSender.send(event.getMessage());
    }
}
