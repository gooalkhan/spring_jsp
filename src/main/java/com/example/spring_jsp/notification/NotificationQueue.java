package com.example.spring_jsp.notification;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class NotificationQueue implements DisposableBean {

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private final Map<String, LinkedBlockingQueue<String>> messageQueueMap = new ConcurrentHashMap<>();

    public void addQueue(String sid) {
        messageQueueMap.put(sid, new LinkedBlockingQueue<>());
    }

    public LinkedBlockingQueue<String> getQueue(String sid) {
        return messageQueueMap.get(sid);
    }

    public void removeQueue(String sid) {
        messageQueueMap.remove(sid);
    }

    public void addSessionToMap(String sid, WebSocketSession session) {
        if (sessionMap.containsKey(sid)) {
            sessionMap.remove(sid);
            sessionMap.put(sid, session);
        } else {
            sessionMap.put(sid, session);
        }

        sessionMap.put(sid, session);
    }

    public void removeSessionFromMap(String sid) {
        sessionMap.remove(sid);
    }

    public WebSocketSession getSessionFromMap(String sid) {
        return sessionMap.get(sid);
    }

    public void sendMessage(NotificationDTO notificationDTO) {
        JSONObject dto = new JSONObject(notificationDTO);
        log.debug(dto.toString());

        String sid = notificationDTO.getSessionId();
        String message = dto.toString();

        if (sessionMap.containsKey(sid)) {
            WebSocketSession session = sessionMap.get(sid);
            try {
                //메시지를 직접 보내고 큐에도 등록. 직접 보낸 메시지를 받았다고 응답이 오면 큐에서 삭제(핸들러에서 처리)
                session.sendMessage(new TextMessage(message));
                addMessage(sid, message);
                log.debug("message {} sent to session: {}",message, sid);
            } catch (IOException | IllegalStateException e) {
                log.warn("session {} is not alive - pushing message to queue", sid);
                addMessage(sid, message);
            }
        } else {
            log.warn("no session for sid: " + sid);
            addMessage(sid, message);
        }
    }

    public void addMessage(String sid, String message) {
        LinkedBlockingQueue<String> queue = messageQueueMap.get(sid);
        if (queue != null) {
            queue.add(message);
            log.debug("message {} added to queue to session: {}", message, sid);
        } else {
            log.warn("no queue for sid: " + sid);
        }
    }

    @Override
    public void destroy() throws Exception {
        for (Map.Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {
            entry.getValue().close();
        }
    }
}
