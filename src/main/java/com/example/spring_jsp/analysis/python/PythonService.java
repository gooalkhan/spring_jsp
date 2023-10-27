package com.example.spring_jsp.analysis.python;


public interface PythonService {

    public void start_process(long bookid, String productId);

    public int insertPython(PythonDTO pythonDTO);

    public PythonDTO selectPython(long bookId, String productId);

}
