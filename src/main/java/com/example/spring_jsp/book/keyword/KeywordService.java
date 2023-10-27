package com.example.spring_jsp.book.keyword;

import java.util.List;

public interface KeywordService {

    public void createTable();

    public void dropTable();

    public List<KeywordDTO> keywordSelect(long bookid);

    public int keywordInsert(KeywordDTO keywordDTO);

    public int keywordCount(long bookid);

}
