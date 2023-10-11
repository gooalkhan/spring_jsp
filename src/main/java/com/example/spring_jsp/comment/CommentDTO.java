package com.example.spring_jsp.comment;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private int idx;
    private String content;
    private Timestamp postDate;
    private String membertbl_id;
    private int boardtbl_idx;
}
