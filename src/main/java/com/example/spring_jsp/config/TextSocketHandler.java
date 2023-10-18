package com.example.spring_jsp.config;

import com.example.spring_jsp.notification.NotificationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@RequiredArgsConstructor
@Component
public class TextSocketHandler extends TextWebSocketHandler {

    private final NotificationService notificationService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        logger.info("message: " + message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        logger.debug("session: " + webSocketSession + " connected");
        HttpSession session = (HttpSession) webSocketSession.getAttributes().get("session");
        session.setAttribute("webSocketSessionId", webSocketSession.getId());
        notificationService.addSession(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status){
        HttpSession session = (HttpSession) webSocketSession.getAttributes().get("session");
        session.removeAttribute("webSocketSessionId");
        notificationService.removeSession(webSocketSession);
        logger.debug("session: " + webSocketSession + " closed");
    }
}