package com.example.spring_jsp.worldcupimage;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WorldCupImageDTO {
	private int idx;
	private String originImageName;
	private String imageName;
	private String imagePath;
	private Timestamp imageDate;
	private String content;
	private int worldcuptbl_idx;
}
