package com.example.spring_jsp;

import com.example.spring_jsp.board.BoardServiceImpl;
import com.example.spring_jsp.comment.CommentServiceImpl;
import com.example.spring_jsp.member.MemberServiceImpl;
import com.example.spring_jsp.notification.NotificationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
//    private final PythonService pythonService;
//
//    private final MemberServiceImpl memberServiceImpl;
//    private final BoardServiceImpl boardServiceImpl;
//    private final CommentServiceImpl commentServiceImpl;
    private final NotificationService notificationService;

    @GetMapping("/")
    public String index(HttpSession session) {
        logger.info("welcome home");
        logger.info("os: " + System.getProperty("os.name"));
        logger.info("user: " + System.getProperty("user.name"));

        Object sid = session.getAttribute("sid");

        if (sid != null) {

            Object webSocketSessionId = session.getAttribute("webSocketSessionId");
            if (webSocketSessionId != null) {
                logger.info("webSocketSessionId: " + webSocketSessionId);

                notificationService.send(webSocketSessionId.toString(), "hello " + sid.toString());
            }
        }

        return "index";
    }
    @GetMapping("/temp")
    public String temp() {
    	return "/indexTEMP";
    }
}
