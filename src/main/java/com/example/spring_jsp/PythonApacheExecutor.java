package com.example.spring_jsp;

import lombok.RequiredArgsConstructor;
import org.apache.commons.exec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

@RequiredArgsConstructor
public class PythonApacheExecutor implements Runnable {

    private final String pythonExecutablePath;
    final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void run() {
        try {
            CommandLine cmdLine = new CommandLine(pythonExecutablePath);

            // 파이썬 실행환경을 resources 폴더에 추가한 경우
            URL url = this.getClass().getResource("/python/main.py");
            File file = new File(url.toURI());

            cmdLine.addArgument("-u");
            cmdLine.addArgument(file.getAbsolutePath());

            Executor executor = getExecutor();
            logger.info("java - python process spawned");
            executor.execute(cmdLine);
            
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private Executor getExecutor() {

        LogOutputStream outputStream = new LogOutputStream() {
            @Override
            protected void processLine(String line, int level) {
                logger.info("python: " + line);
            }
        };

        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        return executor;
    }
}
