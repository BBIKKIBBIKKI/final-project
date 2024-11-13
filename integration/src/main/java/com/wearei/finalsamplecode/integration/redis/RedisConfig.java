package com.wearei.finalsamplecode.integration.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    // Redis 연결을 위한 RedisConnectionFactory 생성 (Redis 클라이언트로 Lettuce 사용)
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(redisHost, redisPort);  // .properties 파일에서 설정한 host, port 가져와 연동
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(new StringRedisSerializer()); // String 직렬화
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // Json 직렬화
        return template;
    }
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10)); // 만료 시간 설정
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(redisCacheConfiguration)
//                .build();
//    }


//    // 메세지 저장되는 컨테이너 팩토리 설정
//    @Bean
//    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic("notification-channel"));
//        return container;
//    }
//
//    // redis pub / sub 사용하여 알림 받는 기능
//    @Bean
//    public MessageListenerAdapter listenerAdapter(RedisSubscribe redisSubscriber) {
//        return new MessageListenerAdapter(redisSubscriber, "handleMessage");
//
//    }
}



