package com.example.spring_jsp.analysis;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AnalysisServiceImpl {

    public ModelAndView getKeywordAnalysis() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("book/analysis/template/keyword");
        return mav;
    }

    public ModelAndView getFavoriteAnalysis() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("book/analysis/template/favorite");
        return mav;
    }
}
