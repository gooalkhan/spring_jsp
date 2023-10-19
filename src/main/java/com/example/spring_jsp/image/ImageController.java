package com.example.spring_jsp.image;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	private final ImageService imageService;

}
