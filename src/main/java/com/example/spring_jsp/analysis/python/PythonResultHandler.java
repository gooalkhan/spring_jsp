package com.example.spring_jsp.analysis.python;

import com.example.spring_jsp.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PythonResultHandler implements ExecuteResultHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final NotificationService notificationService;

    @Override
    public void onProcessComplete(int exitValue) {
        //TODO: 완료된 작업의 북넘버와 프로덕트 아이디를 가져와서 구독자들에게 완료됨 알려주기
        logger.info("python process completed");
    }

    @Override
    public void onProcessFailed(ExecuteException e) {
        logger.error("python process failed");
        logger.error(e.getMessage());
    }
}
