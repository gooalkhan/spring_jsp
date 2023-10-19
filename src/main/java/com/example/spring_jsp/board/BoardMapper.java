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

    
    
	List<BoardDTO> boardSelect();
	int boardInsert(BoardDTO boardDTO);
	int boardUpdate(BoardDTO boardDTO);
	int boardDelete(BoardDTO boardDTO);
	BoardDTO boardDetail(BoardDTO boardDTO);
	List<BoardDTO> boardSearch(String subject);
	List<BoardDTO> commentShow(BoardDTO boardDTO);
	int boardView(int idx);
	BoardDTO boardDetailJoin(BoardDTO boardDTO);
	List<BoardDTO> boardListJoin();
	BoardDTO likeButton(BoardDTO boardDTO);
	int imageUpload(BoardDTO boardDTO);
}
