package com.example.spring_jsp.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WorldCupConfig implements WebMvcConfigurer, InitializingBean {
	
	@Value("${resource.worldcup.path}")
	private String resourceWindowsPath;

	@Value("${resource.linux.worldcup.path}")
	private String resourceLinuxPath;
	
	@Value("${worldcupimages.path}")
	private String uploadPath;

	private String resourcePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uploadPath)
		.addResourceLocations(resourcePath);
	}

	@Override
	public void afterPropertiesSet() {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			resourcePath = resourceWindowsPath;
		} else {
			resourcePath = resourceLinuxPath.formatted(System.getProperty("user.name"));
		}
	}
}
