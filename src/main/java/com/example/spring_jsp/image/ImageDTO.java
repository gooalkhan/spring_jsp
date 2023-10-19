package com.example.spring_jsp.image;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ImageDTO {
	private int idx;
	private String originImageName;
	private String imageName;
	private String imagePath;
	private Timestamp imageDate;
	private int boardtbl_idx;
}
