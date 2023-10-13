package com.example.spring_jsp.comment;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
	//commenttbl에 있는 컬럼들의 값을 담기 위한 변수
    private int idx;
    private String content;
    private Timestamp postDate;
    private String membertbl_id;
    private int boardtbl_idx;
    
    //그 외에 값을 담기 위해 만든 변수
    private int bidx;
}
