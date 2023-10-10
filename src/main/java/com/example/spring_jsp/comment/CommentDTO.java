package com.example.spring_jsp.comment;

import lombok.Data;

@Data
public class CommentDTO {
    private int idx;
    private String subject;
    private String content;
    private String postdate;
    private int likes;
    private int views;
    private int membertbl_idx;
}
