package com.example.spring_jsp.comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {
	List<CommentDTO> boardSelect();
	int boardInsert(Map<String, Object> map);
	int boardUpdate(Map<String, Object> map);
	int boardDelete(Map<String, Object> map);
	CommentDTO boardDetail(int idx);
}
