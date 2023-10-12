package com.example.spring_jsp.board;

import java.util.List;

public interface BoardService {

	void printAll();
	
	List<BoardDTO> boardSelect() throws Exception;
	
	String boardInsert(BoardDTO boardDTO);
	
	BoardDTO boardDetail(int idx) throws Exception;

	boolean boardUpdate(BoardDTO boardDTO);

	boolean boardDelete(BoardDTO boardDTO);

	List<BoardDTO> boardSearch(String subject) throws Exception;

	List<BoardDTO> commentShow(int idx) throws Exception;

}