package com.wearei.finalsamplecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FinalSampleCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinalSampleCodeApplication.class, args);
    }
}
