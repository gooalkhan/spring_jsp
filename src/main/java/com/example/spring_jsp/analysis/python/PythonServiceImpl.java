package com.example.spring_jsp.analysis.python;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PythonServiceImpl implements PythonService {

    private final PythonExecutor pythonExecutor;
    private final PythonMapper pythonMapper;

    public void startAnalysis(long bookid, String productId) {
        pythonExecutor.runAnalysis(bookid, productId);
    }

    public void startUpdateBooks() {

    }

    //인서트는 파이썬에서만 할거라 사실상 안쓰는 부분
    public int insertPython(PythonDTO pythonDTO) {
        return pythonMapper.insertPython(pythonDTO);
    }

    public PythonDTO selectPython(long bookId, String productId) {
        return pythonMapper.selectPython(bookId, productId);
    }
}