package com.example.spring_jsp.worldcup;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WorldCupController {
	private final WorldCupService worldCupService;

}
