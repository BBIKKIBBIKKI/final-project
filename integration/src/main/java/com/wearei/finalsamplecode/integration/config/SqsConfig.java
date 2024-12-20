package com.wearei.finalsamplecode.integration.config;

import com.wearei.finalsamplecode.integration.annotation.AppProfile;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@AppProfile
@Configuration
public class SqsConfig extends AwsConfig {
    /*
    * 0. 클라이언트 key, region 설정
    * 1. SQS 클라이언트를 비동기 방식으로 사용할 수 있게 해주는 메서드
    * 2. 빈으로 등록하여, SQS 에 메세지 전송할 수 있게 해줌
    * */
    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .credentialsProvider(() -> new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return getAccessKey();
                    }

                    @Override
                    public String secretAccessKey() {
                        return getSecretKey();
                    }
                })
                .region(Region.AP_NORTHEAST_2)
                .build();
    }

    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory() {
        return SqsMessageListenerContainerFactory.builder()
                .sqsAsyncClient(sqsAsyncClient())
                .build();
    }

    @Bean
    public SqsTemplate sqsTemplate() {
        return SqsTemplate.newTemplate(sqsAsyncClient());
    }
}
