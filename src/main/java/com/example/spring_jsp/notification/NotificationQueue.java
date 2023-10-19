package com.example.spring_jsp.notification;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class NotificationQueue implements DisposableBean {


    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, LinkedBlockingQueue<String>> map = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void addQueue(String sid) {
        map.put(sid, new LinkedBlockingQueue<>());
    }

    public LinkedBlockingQueue<String> getQueue(String sid) {
        return map.get(sid);
    }

    public void removeQueue(String sid) {
        map.remove(sid);
    }

    public void addSession(String sid, WebSocketSession session) {
        if (sessions.containsKey(sid)) {
            sessions.remove(sid);
            sessions.put(sid, session);
        } else {
            sessions.put(sid, session);
        }

        sessions.put(sid, session);
    }

    public void removeSession(String sid) {
        sessions.remove(sid);
    }

    public WebSocketSession getSession(String sid) {
        return sessions.get(sid);
    }

    public void sendMessage(NotificationDTO notificationDTO) {
        JSONObject dto = new JSONObject(notificationDTO);
        logger.debug(dto.toString());

        String sid = notificationDTO.getSessionId();
        String message = dto.toString();

        if (sessions.containsKey(sid)) {
            WebSocketSession session = sessions.get(sid);
            try {
                session.sendMessage(new TextMessage(message));
                addMessage(sid, message);
                logger.debug("message {} sent to session: {}",message, sid);
            } catch (IOException | IllegalStateException e) {
                logger.warn("session {} is not alive - pushing message to queue", sid);
                addMessage(sid, message);
            }
        } else {
            logger.warn("no session for sid: " + sid);
            addMessage(sid, message);
        }
    }

    public void addMessage(String sid, String message) {
        LinkedBlockingQueue<String> queue = map.get(sid);
        if (queue != null) {
            queue.add(message);
            logger.debug("message {} added to queue to session: {}", message, sid);
        } else {
            logger.warn("no queue for sid: " + sid);
        }
    }

    @Override
    public void destroy() throws Exception {
        for (Map.Entry<String, WebSocketSession> entry : sessions.entrySet()) {
            entry.getValue().close();
        }
    }
}
