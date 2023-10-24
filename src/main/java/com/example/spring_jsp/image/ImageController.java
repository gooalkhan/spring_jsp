package com.example.spring_jsp.image;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@Controller
public class ImageController {
	private final ImageService imageService;

	//이미지 요청시 해당 이미지를 파일시스템(설정에서 변경가능)에서 찾아서 반환
	@GetMapping("/images/{filename:.+}")
	public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
		Resource resource = imageService.getImage(filename);

		byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

		// TODO: 이미지 파일 종류에 맞추어 컨텐트 타입 변경
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG) // 이미지 파일 타입에 따라 Content-Type을 설정합니다.
				.body(imageBytes);
	}
}
