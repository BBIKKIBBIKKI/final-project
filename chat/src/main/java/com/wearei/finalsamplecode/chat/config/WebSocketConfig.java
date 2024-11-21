package com.wearei.finalsamplecode.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); // 메시지 구독 경로
        config.setApplicationDestinationPrefixes("/pub"); // 메시지 발행 경로
    }

    /**
     * /chat path 로 연결 시 웹 소켓 연결
     * setAllowedOrigins("*") -> 도메인 허용 설정
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")  // /chat 경로로 WebSocket 연결
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();  // SockJS 사용 설정
    }
}