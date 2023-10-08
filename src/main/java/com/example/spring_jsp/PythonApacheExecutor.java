package com.example.spring_jsp;

import lombok.RequiredArgsConstructor;
import org.apache.commons.exec.*;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class PythonApacheExecutor implements Runnable {

    private final String pythonExecutablePath;

    private final String profile;

    final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void run() {
        try {
            CommandLine cmdLine = new CommandLine(pythonExecutablePath);

            // 환경 변수 설정
            Map<String, String> environmentVariables = new HashMap<>();
            environmentVariables.put("spring_profiles_active", profile);

            // 환경 변수를 커맨드에 적용
            EnvironmentUtils.addVariableToEnvironment(environmentVariables, "spring_profiles_active="+profile);

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
