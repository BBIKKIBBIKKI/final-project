package com.wearei.finalsamplecode.core.domain.lock.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("redis.stock")
public class StockProperty {
    private String prefix;
}
