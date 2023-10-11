package com.example.spring_jsp.comment;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private int idx;
    private String content;
    private Timestamp postdate;
    private String membertbl_ID;
    private int boardtbl_IDX;
}
