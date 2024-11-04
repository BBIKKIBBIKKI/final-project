package com.wearei.finalsamplecode.integration.listener;

import com.wearei.finalsamplecode.common.event.SQSEvent;
import com.wearei.finalsamplecode.integration.sqs.SQSApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final SQSApi sqsApi;

    @EventListener(SQSEvent.class)
    public void send(SQSEvent event) {
        sqsApi.send(event.getMessage());
    }
}
