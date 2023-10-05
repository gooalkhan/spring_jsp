package com.example.spring_jsp;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class PythonController {

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathWindows;

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathLinux;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/python_start")
    @ResponseBody
    public String start_process() {

        PythonScriptExecutor pythonScriptExecutor;

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            pythonScriptExecutor = new PythonScriptExecutor(pythonExecutablePathWindows.formatted(System.getProperty("user.name")));
        } else {
            pythonScriptExecutor = new PythonScriptExecutor(pythonExecutablePathLinux);
        }

        Thread backgroundThread = new Thread(pythonScriptExecutor);
        backgroundThread.start();
        logger.info("java - python process start");
        return "";
    }
}