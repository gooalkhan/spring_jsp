package com.example.spring_jsp.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {
	List<BoardDTO> boardSelect();
//	int boardInsert(Map<String, Object> map);
//	int boardInsert(BoardDTO boardDTO);
	void boardInsert(BoardDTO boardDTO);
	int boardUpdate(Map<String, Object> map);
	int boardDelete(Map<String, Object> map);
	BoardDTO boardDetail(int idx);
}
