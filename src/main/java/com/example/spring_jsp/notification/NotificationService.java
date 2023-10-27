package com.example.spring_jsp.notification;

public interface NotificationService {

    void initSession(String sid);

    void destroySession(String sid);

    void send(String sessionid, String message);

}
