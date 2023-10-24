package com.example.spring_jsp.notification;

import com.example.spring_jsp.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class NotificationTopicService {

    private final Map<String, NotificationTopicDTO> topics = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BookServiceImpl bookService;
    private final NotificationQueue notificationQueue;

    private void addTopic(long bookId, String productId) {
        if (getTopic(bookId, productId) == null) {
            String bookName = bookService.bookSelect(bookId).getTitle();

            NotificationTopicDTO topic = new NotificationTopicDTO();

            topic.setBookId(bookId);
            topic.setBookName(bookName);
            topic.setProductId(productId);
            topics.put(bookId + ":" + productId, topic);
            logger.debug("topic {} : {} added", bookId, productId);
        }
    }

    public void removeTopicWhenComplete(long bookId, String productId) {
        NotificationTopicDTO topic = getTopic(bookId, productId);

        if (topic != null) {
            String message = topic.getCompleteMessage();

            logger.debug("sending {} to {} subscribers", message, topic.getSubscribers().size());
            for (String sid : topic.getSubscribers()) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setSessionId(sid);
                notificationDTO.setType("message");
                notificationDTO.setMessage(message);

                notificationQueue.sendMessage(notificationDTO);
            }
            topics.remove(bookId + ":" + productId);
            logger.debug("topic {} : {} removed", bookId, productId);
        } else {
            logger.error("topic {} : {} not found", bookId, productId);
        }
    }

    public NotificationTopicDTO getTopic(long bookId, String productId) {
        return topics.get(bookId + ":" + productId);
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
        logger.debug("subscriber {} added to topic {} : {} ", sid, bookId, productId);
    }
}
