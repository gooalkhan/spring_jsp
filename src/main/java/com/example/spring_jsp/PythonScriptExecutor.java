package com.example.spring_jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Component
public class PythonScriptExecutor {

    @Value("${python.executable.path}")
    private String pythonExecutablePath;
    final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    public void run() {
        try {
            // 파이썬 실행환경을 resources 폴더에 추가한 경우
            URL url = this.getClass().getResource("/python/main.py");
            URI uri = url.toURI();

            logger.info(pythonExecutablePath);

            // 파이썬 스크립트 실행
            Process process = Runtime.getRuntime().exec(pythonExecutablePath + " " + uri.getPath());

            // 프로세스의 출력을 읽을 수 있음
             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
             String line;
             while ((line = reader.readLine()) != null) {
                 System.out.println(line);
             }

            // 프로세스가 완료될 때까지 대기
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Python 스크립트가 성공적으로 실행되었습니다.");
            } else {
                logger.info("Python 스크립트 실행 중 오류가 발생했습니다.");
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            logger.error(e.getMessage());
        }
    }
}