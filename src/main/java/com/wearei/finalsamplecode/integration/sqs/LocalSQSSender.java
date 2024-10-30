package com.wearei.finalsamplecode.integration.sqs;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"default"})
public class LocalSQSSender implements SQSSender {
    @Override
    public void send(String message) {
    }
}
