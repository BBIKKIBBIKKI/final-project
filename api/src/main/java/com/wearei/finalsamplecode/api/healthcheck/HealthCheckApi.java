package com.wearei.finalsamplecode.api.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {

    @GetMapping("/")
    public String home() {
        return "api 어플리케이션 헬스체크 정상적";
    }
}
