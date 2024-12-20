package com.wearei.finalsamplecode.integration.sqs;

import com.wearei.finalsamplecode.integration.annotation.AppProfile;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Component
@AppProfile
public class DefaultSQSApi implements SQSApi {
    private final SqsTemplate sqsTemplate;

    @Value("${cloud.aws.sqs.queue-name.queue}")
    private String queue;

    public DefaultSQSApi(SqsAsyncClient sqsAsyncClient) {
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
