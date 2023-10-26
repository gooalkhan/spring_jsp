package com.example.spring_jsp.config;

import java.util.Map;
import com.example.spring_jsp.notification.NotificationQueue;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private final NotificationQueue notificationQueue;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpSession session = servletRequest.getServletRequest().getSession();

            if (session != null && session.getAttribute("sid") != null) {

                    String sid = (String)session.getAttribute("sid");
                    attributes.put("session", session);

                    if (notificationQueue.getQueue(sid) == null) {
                        notificationQueue.addQueue(sid);
                        log.debug("message queue added for sid: " + sid);
                    }
                }
            }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
