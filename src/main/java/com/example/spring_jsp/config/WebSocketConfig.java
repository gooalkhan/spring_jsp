package com.example.spring_jsp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 웹 소켓 설정
 * 웹 소켓 핸들러를 등록한다.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final TextSocketHandler textSocketHandler;
    private final WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(textSocketHandler, "/message").setAllowedOriginPatterns("*").withSockJS().setInterceptors(webSocketHandshakeInterceptor);
    }
}