package com.wearei.finalsamplecode.integration.sqs;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Component
@Profile({"dev", "prod", "local"})
public class DefaultSQSSender implements SQSSender {
    private final SqsTemplate sqsTemplate;

    @Value("${cloud.aws.sqs.queue-name.queue}")
    private String queue;

    public DefaultSQSSender(SqsAsyncClient sqsAsyncClient) {
        this.sqsTemplate = SqsTemplate.newTemplate(sqsAsyncClient);
    }

    @Override
    public void send(String message) {
        sqsTemplate.send(to -> to
                .queue(queue)
                .payload(message)
        );
    }
}
