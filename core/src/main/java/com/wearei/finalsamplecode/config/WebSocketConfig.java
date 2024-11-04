package com.wearei.finalsamplecode.config;

import com.wearei.finalsamplecode.domain.chat.repository.ChatRepository;
import com.wearei.finalsamplecode.domain.chat.service.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatRepository roomRepository;

    /**
     * /chat path 로 연결 시 웹 소켓 연결
     * setAllowedOrigins("*") -> 도메인 허용 설정
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), "/chat").setAllowedOrigins("*");
    }

    @Bean
    public ChatHandler chatHandler() {
        return new ChatHandler(roomRepository);
    }
}