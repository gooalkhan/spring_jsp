package com.example.spring_jsp.notification;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private String id;
    private String type;
    private String message;
    private String sessionId;
    private LocalDateTime messageDate;
}
