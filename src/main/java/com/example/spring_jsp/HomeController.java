package com.example.spring_jsp;

import com.example.spring_jsp.board.BoardServiceImpl;
import com.example.spring_jsp.comment.CommentServiceImpl;
import com.example.spring_jsp.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
//    private final PythonService pythonService;
//
//    private final MemberServiceImpl memberServiceImpl;
//    private final BoardServiceImpl boardServiceImpl;
//    private final CommentServiceImpl commentServiceImpl;

    @GetMapping("/")
    public String index() {
        logger.info("welcome home");
        logger.info("os: " + System.getProperty("os.name"));
        logger.info("user: " + System.getProperty("user.name"));

        return "index";
    }
    @GetMapping("/temp")
    public String temp() {
    	return "/indexTEMP";
    }
}
