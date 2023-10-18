package com.example.spring_jsp.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDTO {
	//boardtbl에 있는 컬럼들의 값을 담기 위한 변수
    private int idx;
    private String subject;
    private String content;
    private Timestamp postDate;
    private int likes;
    private int views;
    private String membertbl_id;
    
    //그 외에 값을 담기 위해 만든 변수
    private String name;
    private String id;
}
