package com.example.spring_jsp.image;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService, InitializingBean {
    private final ImageMapper imageMapper;

    @Value("${resource.images.path}")
    private String imagePathWindows;

    @Value("${resource.linux.images.path}")
    private String imagePathLinux;

    private String imagePath;

    //파일시스템에서 이미지를 가져와 컨트롤러에게 전달
    public Resource getImage(String fileName) {
        File file = new File(imagePath + fileName);
        return new FileSystemResource(file);
    }

    //설정(properties)에 따라 이미지 저장 경로를 설정, 없으면 생성
    @Override
    public void afterPropertiesSet() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            imagePath = imagePathWindows;
        } else {
            imagePath = imagePathLinux.formatted(System.getProperty("user.name"));
        }

        try {
            Files.createDirectories(Paths.get(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
