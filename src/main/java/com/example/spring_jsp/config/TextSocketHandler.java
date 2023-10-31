package com.example.spring_jsp.config;

import com.example.spring_jsp.notification.NotificationQueue;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 웹 소켓의 text message를 처리하는 핸들러
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TextSocketHandler extends TextWebSocketHandler {

    private final NotificationQueue notificationQueue;

    // 클라이언트로부터 메시지가 도착하면 호출되는 메소드, 도착한 메시지가 메시지 큐에 있는 메시지일 경우, 중복 메시지를 삭제하고,
    // 큐에 남은 메시지를 하나 전송한다.
    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) {
        String uuid = message.getPayload();
        log.debug("id: " + message.getPayload() + " received");

        Object session =  webSocketSession.getAttributes().get("session");
        if (session != null && ((HttpSession)session).getAttribute("sid") != null) {
            HttpSession httpSession = (HttpSession) session;
            String sid = httpSession.getAttribute("sid").toString();

            LinkedBlockingQueue<String> queue = notificationQueue.getQueue(sid);

            String messageToSend;
            if ((messageToSend = queue.poll()) != null) {
                if (!messageToSend.contains(uuid)) {
                    try {
                        webSocketSession.sendMessage(new TextMessage(messageToSend));
                        log.debug("message: " + messageToSend + "sent");
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
                log.debug("same message {} received, skipping", uuid);
            }
            log.debug("{} messages are left in queue:",queue.size());
        }

    }

    // 클라이언트가 연결되면 호출되는 메소드, 세션에 sid가 있으면, sid를 키로 하여 세션을 맵에 추가한다.
    // 세션은 notificationQueue의 세션풀에 저장된다.
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.debug("session: " + webSocketSession + " connected");
        Object session =  webSocketSession.getAttributes().get("session");
        if (session != null && ((HttpSession)session).getAttribute("sid") != null) {
            HttpSession httpSession = (HttpSession) session;
            String sid = httpSession.getAttribute("sid").toString();

            notificationQueue.addSessionToMap(sid, webSocketSession);

            LinkedBlockingQueue<String> queue = notificationQueue.getQueue(sid);

            String message = queue.poll();

            if (message != null) {
                try {
                    webSocketSession.sendMessage(new TextMessage(message));
                    log.debug("message: " + message + "sent");
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

        }
    }

    // 클라이언트가 연결을 끊으면 호출되는 메소드, 세션에 sid가 있으면, sid를 키로 하여 세션을 맵에서 제거한다.
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status){
        HttpSession session = (HttpSession) webSocketSession.getAttributes().get("session");
        try {
            if (session != null && session.getAttribute("sid") != null) {
                String sid = session.getAttribute("sid").toString();
                notificationQueue.removeSessionFromMap(sid);
                log.debug("sid {} removed from sessionMap", sid);
            }
            log.debug("session: " + webSocketSession + " closed");
        } catch (IllegalStateException e) {
            log.error("session is already invalidated");
        }
    }
}