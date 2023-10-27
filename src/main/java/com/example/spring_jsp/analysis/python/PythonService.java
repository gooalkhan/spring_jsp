package com.example.spring_jsp.analysis.python;


public interface PythonService {

    void start_analysis_thread(long bookid, String productId);

    int insertPython(PythonDTO pythonDTO);

    PythonDTO selectPython(long bookId, String productId);
}
