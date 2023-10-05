package com.example.spring_jsp;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PythonService {

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathWindows;

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathLinux;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public String start_process() {

        PythonApacheExecutor pythonApacheExecutor;

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            pythonApacheExecutor = new PythonApacheExecutor(pythonExecutablePathWindows.formatted(System.getProperty("user.name")));
        } else {
            pythonApacheExecutor = new PythonApacheExecutor(pythonExecutablePathLinux);
        }

        pythonApacheExecutor.run();
        return "";
    }
}