package com.example.spring_jsp.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ImageController {
	private final ImageService imageService;

	//이미지 요청시 해당 이미지를 파일시스템(설정에서 변경가능)에서 찾아서 반환
	@GetMapping("/images/{filename:.+}")
	public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
		Resource resource = imageService.getImage(filename);

		byte[] imageBytes = new byte[0];
		try {
			imageBytes = Files.readAllBytes(resource.getFile().toPath());
		} catch (IOException e) {
			log.error("file {} not found!", e.getMessage());
		}

		// TODO: 이미지 파일 종류에 맞추어 컨텐트 타입 변경
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG) // 이미지 파일 타입에 따라 Content-Type을 설정합니다.
				.body(imageBytes);
	}
}
