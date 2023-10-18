package com.example.spring_jsp.notification;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    public void addSession(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    public WebSocketSession getSession(String sessionid) {
        return sessions.get(sessionid);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session.getId());
    }

    public void send(String sessionid, String message) {
        WebSocketSession session = getSession(sessionid);

        if (session == null || !session.isOpen()) {
            LOGGER.error("WebSocket session is null or closed");
        } else {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                LOGGER.error("Exception while sending a message", e);
            }
        }
    }
}