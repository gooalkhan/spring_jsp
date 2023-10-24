package com.example.spring_jsp.image;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService, InitializingBean {
    private final ImageMapper imageMapper;

    @Value("${python.images.path.windows}")
    private String pythonImagePathWindows;

    @Value("${python.images.path.linux}")
    private String pythonImagePathLinux;

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
            imagePath = pythonImagePathWindows;
        } else {
            imagePath = pythonImagePathLinux.formatted(System.getProperty("user.name"));
        }

        try {
            Files.createDirectories(Paths.get(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //샘플 사진(박은빈) 생성
        if (!Files.exists(Paths.get(imagePath + "peb.jpg"))) {
            URL url = this.getClass().getResource("/sample_data/peb.jpg");
            try {
                File file = new File(url.toURI());

                Path copied = Paths.get(imagePath + "peb.jpg");
                Path originalPath = file.toPath();

                Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
