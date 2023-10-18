package com.example.spring_jsp.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NotificationQueue notificationQueue;

    public void initSession(String sid) {
        notificationQueue.addQueue(sid);
    }

    public void destroySession(String sid) {
        notificationQueue.removeQueue(sid);
        notificationQueue.removeSession(sid);
    }

    public void send(String sessionid, String message) {

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setSessionId(sessionid);
        notificationDTO.setMessage(message);
        notificationDTO.setMessageDate(LocalDateTime.now());

        notificationQueue.sendMessage(notificationDTO);
    }
}