package com.example.spring_jsp.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDTO {
    private int idx;
    private String subject;
    private String content;
    private Timestamp postDate;
    private int likes;
    private int views;
    private String membertbl_id;
}
