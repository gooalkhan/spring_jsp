package com.example.spring_jsp.worldcup;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WorldCupDTO {
	// worldcuptbl에 있는 컬럼들의 값을 담기 위한 변수
	private int idx;
	private String subject;
	private String content;
	private String membertbl_id;
	
	// worldcupimagetbl에 있는 컬럼들의 값을 담기 위한 변수
	private String originImageName;
	private String imageName;
	private String imagePath;
	private Timestamp imageDate;
	private int worldcuptbl_idx;
}
