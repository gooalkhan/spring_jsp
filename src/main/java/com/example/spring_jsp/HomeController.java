package com.example.spring_jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring_jsp.member.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final PythonService pythonService;

    private final MemberService memberService;

    @GetMapping("/")
    public String index() {
        logger.info("welcome home");
        logger.info("os: " + System.getProperty("os.name"));
        logger.info("user: " + System.getProperty("user.name"));
        memberService.printAll();
        pythonService.start_process();

        return "index";
    }
    
	@GetMapping("/")
	public String indexTEMP() {
		return "/indexTEMP";
	}
    
}
