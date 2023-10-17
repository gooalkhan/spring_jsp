package com.example.spring_jsp.like;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LikeDTO {
	private int idx;
	private boolean LikeCheck;
	private Timestamp LikeDate;
	private String membertbl_id;
	private int boardtbl_idx;
}
