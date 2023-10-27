package com.example.spring_jsp.analysis.python;

import com.example.spring_jsp.notification.NotificationTopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;


@Slf4j
@RequiredArgsConstructor
@Service
public class PythonServiceImpl implements PythonService, InitializingBean {

    @Value("${python.executable.path.windows}")
    private String pythonExecutablePathWindows;

    @Value("${python.executable.path.linux}")
    private String pythonExecutablePathLinux;

    @Value("${spring.profiles.active}")
    private String profile;

    private String profilePath;

    private final PythonMapper pythonMapper;
    private final NotificationTopicService notificationTopicService;

    public void start_process(long bookid, String productId) {

        PythonApacheExecutor pythonApacheExecutor;

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            pythonApacheExecutor = new PythonApacheExecutor(
                    notificationTopicService,
                    pythonExecutablePathWindows.formatted(System.getProperty("user.name")),
                    profilePath,
                    profile,
                    bookid,
                    productId);
        } else {
            pythonApacheExecutor = new PythonApacheExecutor(
                    notificationTopicService,
                    pythonExecutablePathLinux.formatted(System.getProperty("user.name")),
                    profilePath,
                    profile,
                    bookid,
                    productId);
        }
        log.info("python executor thread start");
        Thread thread = new Thread(pythonApacheExecutor);
        thread.start();
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
    }
}