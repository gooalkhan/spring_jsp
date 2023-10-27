package com.example.spring_jsp.analysis;

import com.example.spring_jsp.analysis.python.PythonDTO;
import com.example.spring_jsp.analysis.python.PythonService;
import com.example.spring_jsp.notification.NotificationTopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final PythonService pythonService;
    private final NotificationTopicService notificationTopicService;

    public ModelAndView getKeywordAnalysis(String sid, long bookId, String productId) {
        PythonDTO pythonDTO = pythonService.selectPython(bookId, productId);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("book/analysis/template/keyword");

        if (pythonDTO == null) {

            //작업 실패여부 확인
            if (!notificationTopicService.isTopicFailed(bookId, productId)) {

                //분석중인 작업 여부 체크해서 없으면 작업 요청
                if (notificationTopicService.getTopic(bookId, productId) == null) {
                    pythonService.start_process(bookId, productId);
                }
                //구독 리스트에 추가
                notificationTopicService.addSubscriber(sid, bookId, productId);
            } else {
                mav.addObject("python", "현재 이 분석은 점검중으로 사용하실 수 없습니다 관리자에게 문의해주세요.");
            }

        } else {

            String templateString = pythonDTO.getStringTemplate();
            templateString = templateString.replaceAll("&lt;", "<");
            templateString = templateString.replaceAll("&gt;", ">");
            templateString = templateString.replaceAll("&apos;", "'");

            mav.addObject("python", templateString);
        }
        return mav;
    }

    public ModelAndView getFavoriteAnalysis(String sid, long bookId, String productId) {
        PythonDTO pythonDTO = pythonService.selectPython(bookId, productId);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("book/analysis/template/favorite");

        if (pythonDTO == null) {

            //작업 실패여부 확인
            if (!notificationTopicService.isTopicFailed(bookId, productId)) {

                //분석중인 작업 여부 체크해서 없으면 작업 요청
                if (notificationTopicService.getTopic(bookId, productId) == null) {
                    pythonService.start_process(bookId, productId);
                }
                //구독 리스트에 추가
                notificationTopicService.addSubscriber(sid, bookId, productId);
            } else {
                mav.addObject("python", "현재 이 분석은 점검중으로 사용하실 수 없습니다 관리자에게 문의해주세요.");
            }

        } else {

            String templateString = pythonDTO.getStringTemplate();
            templateString = templateString.replaceAll("&lt;", "<");
            templateString = templateString.replaceAll("&gt;", ">");
            templateString = templateString.replaceAll("&apos;", "'");

            mav.addObject("python", templateString);
        }
        return mav;
    }
}
