package com.example.spring_jsp.analysis.python;

import com.example.spring_jsp.notification.NotificationTopicService;
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
import java.util.Map;

/**
 * python 실행기
 * 파이썬을 apache common exec을 이용해 실행하기 위한 클래스
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class PythonExecutor implements InitializingBean {

    private final NotificationTopicService notificationTopicService;

    private final Map<String, String> requestMap = Map.ofEntries(
            Map.entry("키워드", "mykeyword.py"),
            Map.entry("선호작품", "favorite.py")
    );
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

    //파이썬 실행시에 필요한 공통인자를 설정한다(파이썬 실행파일 경로, 파이썬 스크립트 경로, 프로파일 경로, 프로파일)
    private CommandLine getDefaultCommandLine(String scriptNameDotPy) {
        CommandLine commandLine = new CommandLine(executablePath);
        commandLine.addArgument("-u");
        commandLine.addArgument(scriptPath+scriptNameDotPy);
        commandLine.addArgument(profilePath);
        commandLine.addArgument(profile);
        return commandLine;
    }

    //파이선 기본 실행기를 설정한다. 파이썬 로그 출력시 바로 로그로 출력하도록 설정한다.
    private Executor getDefaultExecutor() {

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

    //파이썬이 종료되었을 때 실행할 기본 콜백핸들러를 설정한다.
    private ExecuteResultHandler getDefaultResultHandler() {
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

    //웹소설 분석 요청이 들어왔을 때 실행하는 파이썬 프로세스. 비동기로 작동
    @Async
    public void runAnalysis(long bookid, String productId) {
        try {
            CommandLine cmdLine = getDefaultCommandLine(requestMap.get(productId));
            cmdLine.addArgument(String.valueOf(bookid));
            log.debug(cmdLine.toString());

            Executor executor = getDefaultExecutor();
            executor.execute(cmdLine,new ExecuteResultHandler() {
                int counter = 0;
                @Override
                public void onProcessComplete(int exitValue) {
                    notificationTopicService.removeTopicWhenComplete(bookid, productId);
                    log.debug("python process completed for topic {} {}", bookid, productId);
                }

                @Override
                public void onProcessFailed(ExecuteException e) {
                    log.error(e.getMessage());

                    counter++;
                    if (counter < 4) {
                        try {
                            log.debug("python process failed for topic {} {} - retry - {}", bookid, productId, counter);
                            notificationTopicService.sendMessageToTopicAllSubscribers(bookid, productId, " 분석이 실패했습니다. 재시도합니다 시도회수:%d".formatted(counter));
                            executor.execute(cmdLine, this);
                        } catch (IOException ex) {
                            log.error(ex.getMessage());
                        }
                    } else {
                        log.debug("python process failed for topic {} {} - retry failed", bookid, productId);
                        notificationTopicService.removeTopicWhenFailure(bookid, productId);
                    }
                }
            });
            log.debug("java - python process spawned");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    //빈 등록 후에 어떤 운영체제인지에 따라 파이썬 실행파일 경로를 설정한다.
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
