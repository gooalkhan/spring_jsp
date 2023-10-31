package com.example.spring_jsp.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 웹 소켓을 통해 메시지를 전송하는 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationQueue notificationQueue;

    public void initSession(String sid) {
        notificationQueue.addQueue(sid);
    }

    public void destroySession(String sid) {
        notificationQueue.removeQueue(sid);
        notificationQueue.removeSessionFromMap(sid);
    }

    public void send(String sessionid, String message) {

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType("message");
        notificationDTO.setSessionId(sessionid);
        notificationDTO.setMessage(message);
        notificationQueue.sendMessage(notificationDTO);
    }
}