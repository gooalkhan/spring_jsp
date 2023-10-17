package com.example.spring_jsp.like;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikeMapper {
	void createTable();
	
	void dropTable();
}
