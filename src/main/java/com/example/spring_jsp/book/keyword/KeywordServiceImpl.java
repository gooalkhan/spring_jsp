package com.example.spring_jsp.book.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordMapper keywordMapper;

    public void createTable() {
        keywordMapper.createTable();
    }

    public void dropTable() {
        keywordMapper.dropTable();
    }

    public List<KeywordDTO> keywordSelect(long bookid) {
        return keywordMapper.keywordSelect(bookid);
    }

    public int keywordInsert(KeywordDTO keywordDTO) {
        return keywordMapper.keywordInsert(keywordDTO);
    }

    public int keywordCount(long bookid) {
        return keywordMapper.keywordCount(bookid);
    }

}