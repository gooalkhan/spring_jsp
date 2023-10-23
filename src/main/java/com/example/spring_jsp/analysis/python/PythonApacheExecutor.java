package com.example.spring_jsp.analysis.python;

import lombok.RequiredArgsConstructor;
import org.apache.commons.exec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
public class PythonApacheExecutor implements Runnable {

    private final Map<String, String> requestMap = Map.ofEntries(
            Map.entry("키워드", "keyword.py"),
            Map.entry("선호작품", "favorite.py")
    );

    private final String pythonExecutablePath;
    private final PythonResultHandler pythonResultHandler;

    private final String profile;
    private final long bookid;
    private final String productId;

    final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void run() {
        try {
            CommandLine cmdLine = new CommandLine(pythonExecutablePath);

            URL url = this.getClass().getResource("/python/%s".formatted(requestMap.get(productId)));
            File file = new File(url.toURI());

            cmdLine.addArgument("-u");
            cmdLine.addArgument(file.getAbsolutePath());
            cmdLine.addArgument(profile);
            cmdLine.addArgument(String.valueOf(bookid));

            Executor executor = getExecutor();
            executor.execute(cmdLine, pythonResultHandler);
            logger.info("java - python process spawned");
            
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
