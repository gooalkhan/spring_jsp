package com.example.spring_jsp.book.keyword;

import java.util.List;

public interface KeywordService {

    void createTable();

    void dropTable();

    List<KeywordDTO> keywordSelect(long bookid);

    int keywordInsert(KeywordDTO keywordDTO);

    int keywordCount(long bookid);

}
