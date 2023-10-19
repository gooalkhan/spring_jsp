package com.example.spring_jsp.image;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	
public class ImageServiceImpl implements ImageService{
	private final ImageMapper imageMapper;
}
