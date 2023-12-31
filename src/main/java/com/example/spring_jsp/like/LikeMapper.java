package com.example.spring_jsp.like;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikeMapper {
	void createTable();
	
	void dropTable();
	
	
	void likeUpdate(int boardtbl_idx);
	
	void likeUpdateCancel(int boardtbl_idx);
	
	void likeInsert(LikeDTO likeDTO);
	
	void likeDelete(LikeDTO likeDTO);
	
	void likeUpdateCheck(LikeDTO likeDTO);
	
	void likeUpdateCheckCancel(LikeDTO likeDTO);
	
	int likeCheck(LikeDTO likeDTO);
}
