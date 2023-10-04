package com.example.spring_jsp;

import com.example.spring_jsp.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final MemberService memberService;
    private final PythonScriptExecutor pythonScriptExecutor;

    @GetMapping("/")
    public String index() {
        logger.info("welcome home");
        memberService.printAll();
        pythonScriptExecutor.run();
        return "index";
    }
}
