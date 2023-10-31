package com.example.spring_jsp.analysis.python;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 파이썬 서비스
 * 파이썬 실행기와 파이썬 테이블(파이썬 실행결과가 저장되는)을 이용해 파이썬을 실행하고 결과를 저장하는 서비스
 */
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
        //TODO: 책정보 업데이트 하는 기능
    }

    //인서트는 파이썬에서만 할거라 사실상 안쓰는 부분
    public int insertPython(PythonDTO pythonDTO) {
        return pythonMapper.insertPython(pythonDTO);
    }

    public PythonDTO selectPython(long bookId, String productId) {
        return pythonMapper.selectPython(bookId, productId);
    }
}