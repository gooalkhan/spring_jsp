package com.example.spring_jsp.analysis.python;

import com.example.spring_jsp.notification.NotificationTopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RequiredArgsConstructor
@Service
public class PythonServiceImpl implements PythonService, InitializingBean {

    private final PythonMapper pythonMapper;
    private final NotificationTopicService notificationTopicService;

    private final String username = System.getProperty("user.name");

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathWindows;

    @Value("${python.executable.path.linux}")
    private String pythonExecutablePathLinux;

    @Value("${spring.profiles.active}")
    private String profile;

    private String executablePath;
    private String profilePath;


    public void start_analysis_thread(long bookid, String productId) {

        PythonTopicExecutor pythonTopicExecutor;

        pythonTopicExecutor = new PythonTopicExecutor(
                notificationTopicService,
                executablePath,
                profilePath,
                profile,
                bookid,
                productId);

        log.info("python executor thread start");
        Thread thread = new Thread(pythonTopicExecutor);
        thread.start();
    }

    @Async
    public CompletableFuture<String> getNow() {
        String now = LocalDateTime.now().toString();
        // 값을 리턴할 경우 future객체를 리턴하며, 값을 받아오려면 get()으로 명시적 join을 해줘야함
        return CompletableFuture.completedFuture(now);
    }

    @Async
    void bookUpdateWithPython() {

    }

    //인서트는 파이썬에서만 할거라 사실상 안쓰는 부분
    public int insertPython(PythonDTO pythonDTO) {
        return pythonMapper.insertPython(pythonDTO);
    }

    public PythonDTO selectPython(long bookId, String productId) {
        return pythonMapper.selectPython(bookId, productId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        URL url = this.getClass().getResource("/application.properties");
        File file = new File(url.toURI());
        profilePath = file.getParent();

        executablePath = System.getProperty("os.name").toLowerCase().contains("windows") ?
                pythonExecutablePathWindows.formatted(username)
                : pythonExecutablePathLinux.formatted(username);
    }
}