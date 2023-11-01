package com.example.spring_jsp.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageHandlerConfig implements WebMvcConfigurer, InitializingBean {

	@Value("${resource.linux.images.file.path}")
	private String resourceLinuxImagePath;

	@Value("${resource.images.file.path}")
	private String resourceWindowsImagePath;

	@Value("${worldcupimages.path}")
	private String uploadWorldCupPath;

	@Value("${boardimages.path}")
	private String uploadBoardPath;

	private String resourcePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uploadWorldCupPath + "/**")
		.addResourceLocations(resourcePath + uploadWorldCupPath + "/");
		registry.addResourceHandler(uploadBoardPath + "/**")
		.addResourceLocations(resourcePath + uploadBoardPath + "/");
	}

	@Override
	public void afterPropertiesSet() {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			resourcePath = resourceWindowsImagePath;
		} else {
			resourcePath = resourceLinuxImagePath.formatted(System.getProperty("user.name"));
		}
	}
}
