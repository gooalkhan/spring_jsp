package com.example.spring_jsp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BoardConfig implements WebMvcConfigurer{
	
	// application.properties에서 설정한 값을 읽어옴
	@Value("${resource.board.path}")
	private String resourcePath;
	
	@Value("${boardimages.path}")
	private String uploadPath;
	
	// uploadPath가 /boardimages/**이고 resourcePath가 "file:///C:/FileIO/images/boardimages/"로 설정되어 있다면,
	// 클라이언트가 "/boardimages/1.jpg"와 같은 URL을 요청하면 스프링은 해당 요청을 "file:///C:/FileIO/images/boardimages/1.jpg" 경로에서 찾아 응답으로 제공
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uploadPath)
		.addResourceLocations(resourcePath);
	}
}
