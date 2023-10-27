package com.example.spring_jsp.analysis;

import com.example.spring_jsp.analysis.python.PythonDTO;
import org.springframework.web.servlet.ModelAndView;

public interface AnalysisService {

    public ModelAndView getKeywordAnalysis(String sid, long bookId, String productId);

    public ModelAndView getFavoriteAnalysis(String sid, long bookId, String productId);

}
