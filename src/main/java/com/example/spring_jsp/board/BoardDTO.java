package com.example.spring_jsp.board;

import lombok.Data;

@Data
public class BoardDTO {
    private int idx;
    private String subject;
    private String content;
    private String postDate;
    private int likes;
    private int views;
    private String membertbl_id;
}
