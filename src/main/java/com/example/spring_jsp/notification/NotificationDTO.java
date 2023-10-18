package com.example.spring_jsp.notification;

import lombok.Data;
import java.util.Date;

@Data
public class NotificationDTO {
    private String message;
    private String sessionId;
    private Date messageDate;
}
