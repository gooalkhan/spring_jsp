package com.example.spring_jsp;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    //TODO: 첫 화면에서 보여줄 게시글, 새로 업데이트된 웹소설 보여주기

    @GetMapping("/")
    public String index() {
        logger.info("welcome home");
        logger.info("os: " + System.getProperty("os.name"));
        logger.info("user: " + System.getProperty("user.name"));

        return "index";
    }
}
