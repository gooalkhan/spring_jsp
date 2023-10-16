package com.example.spring_jsp.book.keyword;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeywordMapper {
    void createTable();

    void dropTable();

    List<KeywordDTO> keywordSelect(long bookid);

    int keywordInsert(KeywordDTO keywordDTO);

    int keywordCount(long bookid);
}
