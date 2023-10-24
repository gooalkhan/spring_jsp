package com.example.spring_jsp.analysis.python;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;


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
    private final PythonResultHandler pythonResultHandler;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void start_process(long bookid, String productId) {

        PythonApacheExecutor pythonApacheExecutor;

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            pythonApacheExecutor = new PythonApacheExecutor(
                    pythonExecutablePathWindows.formatted(System.getProperty("user.name")),
                    pythonResultHandler,
                    profilePath,
                    profile,
                    bookid,
                    productId);
        } else {
            pythonApacheExecutor = new PythonApacheExecutor(
                    pythonExecutablePathLinux.formatted(System.getProperty("user.name")),
                    pythonResultHandler,
                    profilePath,
                    profile,
                    bookid,
                    productId);
        }
        logger.info("python executor thread start");
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