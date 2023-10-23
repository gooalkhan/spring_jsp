package com.example.spring_jsp.board;

import java.util.List;

public interface BoardService {

	void printAll();
	
	List<BoardDTO> boardSelect() throws Exception;
	
	String boardInsert(BoardDTO boardDTO);
	
	BoardDTO boardDetail(BoardDTO boardDTO) throws Exception;

	boolean boardUpdate(BoardDTO boardDTO);

	boolean boardDelete(BoardDTO boardDTO);

	List<BoardDTO> boardSearch(String subject) throws Exception;
	
	List<BoardDTO> commentShow(BoardDTO boardDTO) throws Exception;
	
	int boardView(int idx);

	BoardDTO boardDetailJoin(BoardDTO boardDTO) throws Exception;

	List<BoardDTO> boardListJoin() throws Exception;

	BoardDTO likeButton(BoardDTO boardDTO) throws Exception;

	String imageUpload(BoardDTO boardDTO);

	List<BoardDTO> imageSelect(BoardDTO boardDTO);

	boolean imageDelete(BoardDTO boardDTO);

}
