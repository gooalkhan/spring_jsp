package com.example.spring_jsp.analysis.python;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import org.apache.commons.exec.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@Component
public class PythonExecutor implements InitializingBean {

    private final PythonMapper pythonMapper;

    private final String username = System.getProperty("user.name");

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathWindows;

    @Value("${python.executable.path.linux}")
    private String pythonExecutablePathLinux;

    @Value("${spring.profiles.active}")
    private String profile;

    private String profilePath;
    private String executablePath;
    private String scriptPath;

    @Async void updateBook() {
        log.debug("start book updating thread...");
        runScript("updatebook.py");
    }

    @Async
    public void runScript(String script) {
        CommandLine commandLine = new CommandLine(executablePath);
        commandLine.addArgument("-u");
        commandLine.addArgument(scriptPath+script);
        commandLine.addArgument(profilePath);
        commandLine.addArgument(profile);

        Executor executor = getExecutor();

        try {
            executor.execute(commandLine, getResultHandler());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private Executor getExecutor() {

        LogOutputStream outputStream = new LogOutputStream() {
            @Override
            protected void processLine(String line, int level) {
                log.info("python: " + line);
            }
        };

        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        return executor;
    }

    private ExecuteResultHandler getResultHandler() {
        return new ExecuteResultHandler() {

            @Override
            public void onProcessComplete(int exitValue) {
                log.debug("python process completed");
            }

            @Override
            public void onProcessFailed(ExecuteException e) {
                log.error(e.getMessage());
            }
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        URL url = this.getClass().getResource("/application.properties");
        File file = new File(url.toURI());
        profilePath = file.getParent();

        scriptPath = System.getProperty("os.name").toLowerCase().contains("windows") ?
                profilePath + "\\python\\"
                : profilePath + "/python/";

        executablePath = System.getProperty("os.name").toLowerCase().contains("windows") ?
                pythonExecutablePathWindows.formatted(username)
                : pythonExecutablePathLinux.formatted(username);
    }
}
