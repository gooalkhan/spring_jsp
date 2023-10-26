package com.example.spring_jsp.config;

import com.example.spring_jsp.notification.NotificationQueue;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@RequiredArgsConstructor
@Component
public class TextSocketHandler extends TextWebSocketHandler {

    private final NotificationQueue notificationQueue;

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {
        String uuid = message.getPayload();
        log.debug("id: " + message.getPayload() + " received");

        Object session =  webSocketSession.getAttributes().get("session");
        if (session != null && ((HttpSession)session).getAttribute("sid") != null) {
            HttpSession httpSession = (HttpSession) session;
            String sid = httpSession.getAttribute("sid").toString();

            LinkedBlockingQueue<String> queue = notificationQueue.getQueue(sid);

            String messageToSend;
            if ((messageToSend = queue.poll()) != null) {
                if (!messageToSend.contains(uuid)) {
                    try {
                        webSocketSession.sendMessage(new TextMessage(messageToSend));
                        log.debug("message: " + messageToSend + "sent");
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                log.debug("same message {} received, skipping", uuid);
            }
            log.debug("{} messages are left in queue:",queue.size());
        }

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.debug("session: " + webSocketSession + " connected");
        Object session =  webSocketSession.getAttributes().get("session");
        if (session != null && ((HttpSession)session).getAttribute("sid") != null) {
            HttpSession httpSession = (HttpSession) session;
            String sid = httpSession.getAttribute("sid").toString();

            notificationQueue.addSessionToMap(sid, webSocketSession);

            LinkedBlockingQueue<String> queue = notificationQueue.getQueue(sid);

            String message = queue.poll();

            if (message != null) {
                try {
                    webSocketSession.sendMessage(new TextMessage(message));
                    log.debug("message: " + message + "sent");
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status){
        HttpSession session = (HttpSession) webSocketSession.getAttributes().get("session");
        try {
            if (session != null && session.getAttribute("sid") != null) {
                String sid = session.getAttribute("sid").toString();
                notificationQueue.removeSessionFromMap(sid);
                log.debug("sid {} removed from sessionMap", sid);
            }
            log.debug("session: " + webSocketSession + " closed");
        } catch (IllegalStateException e) {
            log.error("session is already invalidated");
        }
    }
}