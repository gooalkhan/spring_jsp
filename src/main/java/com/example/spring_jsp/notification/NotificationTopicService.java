package com.example.spring_jsp.notification;

import com.example.spring_jsp.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 분석작업 중인 분석자료마다 생성되는 토픽을 관리하는 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationTopicService {

    //진행중인 토픽들
    private final Map<String, NotificationTopicDTO> topics = new ConcurrentHashMap<>();

    //실패한 토픽들. 분석작업 요청시 실패한 토픽을 찾아보고 실패했으면 이용불가를 표시
    private final Map<String, NotificationTopicDTO> failedTopics = new ConcurrentHashMap<>();

    private final BookService bookService;
    private final NotificationQueue notificationQueue;

    private void addTopic(long bookId, String productId) {
        if (getTopic(bookId, productId) == null) {
            String bookName = bookService.bookSelect(bookId).getTitle();

            NotificationTopicDTO topic = new NotificationTopicDTO();

            topic.setBookId(bookId);
            topic.setBookName(bookName);
            topic.setProductId(productId);
            topics.put(bookId + ":" + productId, topic);
            log.debug("topic {} : {} added", bookId, productId);
        }
    }

    private void sendTopicToFailure(long bookId, String productId) {
        if (getTopic(bookId, productId) != null) {

            NotificationTopicDTO topic = getTopic(bookId, productId);
            failedTopics.put(bookId + ":" + productId, topic);

            removeTopic(bookId, productId);

            log.debug("topic {} : {} moved to failed topic", bookId, productId);
        }
    }

    public void removeTopic(long bookId, String productId) {
        if (getTopic(bookId, productId) != null) {
            topics.remove(bookId + ":" + productId);
            log.debug("topic {} : {} removed", bookId, productId);
        }
    }

    // 토픽의 모든 구독자들에게 메시지 보내기(작업 완료/실패여부)
    public void sendMessageToTopicAllSubscribers(long bookId, String productId, String message) {
        NotificationTopicDTO topic = getTopic(bookId, productId);

        if (topic != null) {
            String messageToSend = topic.getTopicString() + " " + message;

            log.debug("sending {} to {} subscribers", messageToSend, topic.getSubscribers().size());
            for (String sid : topic.getSubscribers()) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setSessionId(sid);
                notificationDTO.setType("message");
                notificationDTO.setMessage(messageToSend);

                notificationQueue.sendMessage(notificationDTO);
            }
        } else {
            log.error("topic {} : {} not found", bookId, productId);
        }
    }

    //분석 작업 완료시 완료 메시지 보내고 토픽 삭제
    public void removeTopicWhenComplete(long bookId, String productId) {
        NotificationTopicDTO topic = getTopic(bookId, productId);

        if (topic != null) {
            sendMessageToTopicAllSubscribers(bookId, productId, " 분석이 완료되었습니다");
            removeTopic(bookId, productId);
            log.debug("topic {} : {} removed", bookId, productId);
        } else {
            log.error("topic {} : {} not found", bookId, productId);
        }
    }

    //분석작업 실패시 실패 메시지 보내고 토픽을 실패 토픽으로 이동
    public void removeTopicWhenFailure(long bookId, String productId) {
        NotificationTopicDTO topic = getTopic(bookId, productId);

        if (topic != null) {
            sendMessageToTopicAllSubscribers(bookId, productId, " 분석이 실패했습니다. 재시도 횟수를 초과했습니다.");
            sendTopicToFailure(bookId, productId);
            log.debug("topic {} : {} removed", bookId, productId);
        } else {
            log.error("topic {} : {} not found", bookId, productId);
        }
    }

    public NotificationTopicDTO getTopic(long bookId, String productId) {
        return topics.get(bookId + ":" + productId);
    }

    public boolean isTopicFailed(long bookId, String productId) {
        return failedTopics.containsKey(bookId + ":" + productId);
    }

    public void addSubscriber(String sid, long bookId, String productId) {
        if (getTopic(bookId, productId) == null) {
            addTopic(bookId, productId);
        }
        NotificationTopicDTO notificationTopicDTO = getTopic(bookId, productId);
        notificationTopicDTO.syncSubscriber(sid);
        String bookName = notificationTopicDTO.getBookName();

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setSessionId(sid);
        notificationDTO.setType("message");
        notificationDTO.setMessage("%s %s분석이 진행중입니다. 완료되면 알려 드리겠습니다".formatted(bookName, productId));

        notificationQueue.sendMessage(notificationDTO);
        log.debug("subscriber {} added to topic {} : {} ", sid, bookId, productId);
    }
}
