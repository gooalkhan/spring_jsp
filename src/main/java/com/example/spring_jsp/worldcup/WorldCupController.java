package com.example.spring_jsp.worldcup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WorldCupController {
	private final WorldCupService worldCupService;
	
	// 이상형 월드컵 만들기 페이지
	@GetMapping("/worldCupCreate")
	public String worldCupCreate() {
		return "/worldcup/worldCupCreate";
	}
}
