package com.example.spring_jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringJspApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringJspApplication.class, args);
	}

	//jsp 사용시 외장톰캣으로 실행하기 위한 추가
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringJspApplication.class);
	}
}
