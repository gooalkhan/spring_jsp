package com.example.spring_jsp.image;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	
public class ImageServiceImpl implements ImageService{
	private final ImageMapper imageMapper;
	
	@Override
	public String imageUpload(ImageDTO imageDTO) {
		int affectRowCount = this.imageMapper.imageUpload(imageDTO);
		if (affectRowCount == 1) {
			return String.valueOf(imageDTO.getIdx());
		}
		return null;
	}
}
