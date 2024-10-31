package com.wearei.finalsamplecode;

import com.wearei.finalsamplecode.integration.sqs.DefaultSQSSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class FinalSampleCodeApplication {
    private final DefaultSQSSender defaultSQSSender;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        defaultSQSSender.send("성공적으로 서버가 실행됨");
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalSampleCodeApplication.class, args);
    }
}
