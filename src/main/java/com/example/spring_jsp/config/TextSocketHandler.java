package com.example.spring_jsp.config;

import com.example.spring_jsp.notification.NotificationQueue;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.LinkedBlockingQueue;

@RequiredArgsConstructor
@Component
public class TextSocketHandler extends TextWebSocketHandler {

    private final NotificationQueue notificationQueue;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {
        String uuid = message.getPayload();
        logger.debug("id: " + message.getPayload() + " received");

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
                        logger.debug("message: " + messageToSend + "sent");
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
                logger.debug("same message {} received, skipping", uuid);
            }
            logger.debug("{} messages are left in queue:",queue.size());
        }

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        logger.debug("session: " + webSocketSession + " connected");
        Object session =  webSocketSession.getAttributes().get("session");
        if (session != null && ((HttpSession)session).getAttribute("sid") != null) {
            HttpSession httpSession = (HttpSession) session;
            String sid = httpSession.getAttribute("sid").toString();

            notificationQueue.addSession(sid, webSocketSession);

            LinkedBlockingQueue<String> queue = notificationQueue.getQueue(sid);

            String message = queue.poll();

            if (message != null) {
                try {
                    webSocketSession.sendMessage(new TextMessage(message));
                    logger.debug("message: " + message + "sent");
                } catch (Exception e) {
                    logger.error(e.getMessage());
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
                notificationQueue.removeSession(sid);
            }
            logger.debug("session: " + webSocketSession + " closed");
        } catch (IllegalStateException e) {
            logger.error("session is already invalidated");
        }
    }
}