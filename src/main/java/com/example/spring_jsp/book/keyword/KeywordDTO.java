package com.example.spring_jsp.book.keyword;

import lombok.Data;

import java.util.Date;

@Data
public class KeywordDTO {
    private int idx;
    private long bookid;
    private String keyword;
    private Date last_update;
}
