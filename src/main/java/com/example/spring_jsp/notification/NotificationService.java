package com.example.spring_jsp.notification;

public interface NotificationService {

    public void initSession(String sid);

    public void destroySession(String sid);

    public void send(String sessionid, String message);

}
