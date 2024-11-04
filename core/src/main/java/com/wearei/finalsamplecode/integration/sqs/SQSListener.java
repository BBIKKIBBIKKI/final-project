package com.wearei.finalsamplecode.integration.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SQSListener {

    @SqsListener("${cloud.aws.sqs.queue-name.queue}")
    public void receive(String message) {
        System.out.println("Listener : " + message);
    }
}
