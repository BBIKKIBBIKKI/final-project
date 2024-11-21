package com.wearei.finalsamplecode.api.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {

    @GetMapping("/")
    public String home() {
        return "{\"status\":\"ok\"}";
    }

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "{\"status\":\"ok\"}";
    }
}
