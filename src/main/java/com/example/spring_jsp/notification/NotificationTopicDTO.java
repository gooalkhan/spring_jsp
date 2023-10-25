package com.example.spring_jsp.notification;

import lombok.Data;
import java.util.List;
import java.util.Vector;

@Data
public class NotificationTopicDTO {
    private long bookId;
    private String bookName;
    private String productId;
    private List<String> subscribers = new Vector<>();

    public void syncSubscriber(String sid) {
        if (!subscribers.contains(sid)) {
            subscribers.add(sid);
        }
    }

    public String getTopicString() {
        return bookName + " " + productId;
    }
}
