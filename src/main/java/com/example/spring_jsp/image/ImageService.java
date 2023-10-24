package com.example.spring_jsp.image;

import org.springframework.core.io.Resource;

public interface ImageService {
    Resource getImage(String filename);
}
