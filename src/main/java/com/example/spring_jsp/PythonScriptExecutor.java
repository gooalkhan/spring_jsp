package com.example.spring_jsp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

@RequiredArgsConstructor
public class PythonScriptExecutor implements Runnable {

    private final String pythonExecutablePath;
    final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Getter
    private Process process;

    public void run() {
        try {
            // 파이썬 실행환경을 resources 폴더에 추가한 경우
            URL url = this.getClass().getResource("/python/main.py");
            File file = new File(url.toURI());

            //logger.info(pythonExecutablePath + " " + file.getAbsolutePath());

            // 파이썬 스크립트 실행
            process = Runtime.getRuntime().exec(pythonExecutablePath + " " + file.getAbsolutePath());

            // 프로세스의 출력을 읽을 수 있음
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }

            int exitcode = process.waitFor();

            if (exitcode == 0) {
                logger.info("java - python process ends successfully");
            } else {
                logger.info("java - python process end with error");
            }

            reader.close();

        } catch (IOException | URISyntaxException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}