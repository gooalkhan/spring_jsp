package com.example.spring_jsp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WorldCupConfig implements WebMvcConfigurer{
	
	@Value("${resource.worldcup.path}")
	private String resourcePath;
	
	@Value("${worldcupimages.path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uploadPath)
		.addResourceLocations(resourcePath);
	}
}
