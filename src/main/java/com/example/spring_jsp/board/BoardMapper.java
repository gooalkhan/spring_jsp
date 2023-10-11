package com.example.spring_jsp.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {
    void createTable();

    void dropTable();

    BoardDTO boardDetail(BoardDTO boardDTO);
    
    //TODO : map으로 된 거 다 DTO로 고칠 예정
	List<BoardDTO> boardSelect();
	int boardInsert(BoardDTO boardDTO);
	int boardUpdate(BoardDTO boardDTO);
	int boardDelete(BoardDTO boardDTO);
	BoardDTO boardDetail(int idx);
}
