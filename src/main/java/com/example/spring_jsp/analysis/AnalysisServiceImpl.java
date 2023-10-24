package com.example.spring_jsp.analysis;

import com.example.spring_jsp.analysis.python.PythonDTO;
import com.example.spring_jsp.analysis.python.PythonServiceImpl;
import com.example.spring_jsp.notification.NotificationTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl {

    private final PythonServiceImpl pythonServiceImpl;
    private final NotificationTopicService notificationTopicService;

    public ModelAndView getKeywordAnalysis(String sid, long bookId, String productId) {
        PythonDTO pythonDTO = pythonServiceImpl.selectPython(bookId, productId);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("book/analysis/template/keyword");

        if (pythonDTO == null) {

            //분석중인 작업 여부 체크해서 없으면 작업 요청
            if (notificationTopicService.getTopic(bookId, productId) == null) {
                pythonServiceImpl.start_process(bookId, productId);
            }
            //구독 리스트에 추가
            notificationTopicService.addSubscriber(sid, bookId, productId);

        } else {

            String templateString = pythonDTO.getStringTemplate();
            templateString = templateString.replaceAll("&lt;", "<");
            templateString = templateString.replaceAll("&gt;", ">");

            mav.addObject("python", templateString);
        }
        return mav;
    }

    public ModelAndView getFavoriteAnalysis() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("book/analysis/template/favorite");
        return mav;
    }
}
