package com.example.spring_jsp.notification;

import lombok.Data;
import java.util.List;
import java.util.Vector;

/**
 * 분석중인 분석자료마다 생성되는 토픽 DTO, 내부의 구독자 목록을 가짐(현재 구매 완료했고 작업이 끝나기를 기다리고 있는 세션)
 * 파이썬이 작업 시작하면 생성되며, 파이썬이 작업을 완료하거나 작업을 실패하면 삭제됨
 */
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
