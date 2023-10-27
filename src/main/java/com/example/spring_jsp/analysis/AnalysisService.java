package com.example.spring_jsp.analysis;

import org.springframework.web.servlet.ModelAndView;

public interface AnalysisService {

    ModelAndView getKeywordAnalysis(String sid, long bookId, String productId);

    ModelAndView getFavoriteAnalysis(String sid, long bookId, String productId);

}
