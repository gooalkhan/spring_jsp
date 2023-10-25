package com.example.spring_jsp.analysis.python;

import com.example.spring_jsp.notification.NotificationTopicService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.exec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
public class PythonApacheExecutor implements Runnable {

    private final Map<String, String> requestMap = Map.ofEntries(
            Map.entry("키워드", "keyword.py"),
            Map.entry("선호작품", "favorite.py")
    );

    private final NotificationTopicService notificationTopicService;
    private final String pythonExecutablePath;
    private final String profilePath;
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
            cmdLine.addArgument(profilePath);
            cmdLine.addArgument(profile);
            cmdLine.addArgument(String.valueOf(bookid));

            Executor executor = getExecutor();
            executor.execute(cmdLine, new ExecuteResultHandler() {
                int counter = 0;
                @Override
                public void onProcessComplete(int exitValue) {
                    notificationTopicService.removeTopicWhenComplete(bookid, productId);
                    logger.debug("python process completed for topic {} {}", bookid, productId);
                }

                @Override
                public void onProcessFailed(ExecuteException e) {
                    logger.error(e.getMessage());

                    counter++;
                    if (counter < 4) {
                        try {
                            logger.debug("python process failed for topic {} {} - retry - {}", bookid, productId, counter);
                            notificationTopicService.sendMessageToTopicAllSubscribers(bookid, productId, " 분석이 실패했습니다. 재시도합니다 시도회수:%d".formatted(counter));
                            executor.execute(cmdLine, this);
                        } catch (IOException ex) {
                            logger.error(ex.getMessage());
                        }
                    } else {
                        logger.debug("python process failed for topic {} {} - retry failed", bookid, productId);
                        notificationTopicService.removeTopicWhenFailure(bookid, productId);
                    }
                }
            });
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
