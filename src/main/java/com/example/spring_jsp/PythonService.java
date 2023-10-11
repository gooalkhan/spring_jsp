package com.example.spring_jsp;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class PythonService {

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathWindows;

    @Value("${python.executable.path.linux}")
    private String pythonExecutablePathLinux;

    @Value("${spring.profiles.active}")
    private String profile;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void start_process() {

        PythonApacheExecutor pythonApacheExecutor;

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            pythonApacheExecutor = new PythonApacheExecutor(pythonExecutablePathWindows.formatted(System.getProperty("user.name")), profile);
        } else {
            pythonApacheExecutor = new PythonApacheExecutor(pythonExecutablePathLinux.formatted(System.getProperty("user.name")), profile);
        }
        logger.info("python executor thread start");
        Thread thread = new Thread(pythonApacheExecutor);
        thread.start();
    }
}