package com.example.spring_jsp.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.Random;

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

        notificationDTO.setId(randomStringGenerator());
        notificationDTO.setType("message");
        notificationDTO.setSessionId(sessionid);
        notificationDTO.setMessage(message);
        notificationDTO.setMessageDate(LocalDateTime.now());

        notificationQueue.sendMessage(notificationDTO);
    }

    public static String randomStringGenerator() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return(random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
    }
}