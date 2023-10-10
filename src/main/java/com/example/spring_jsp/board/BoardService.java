package com.example.spring_jsp.board;

import java.util.List;
import java.util.Map;

public interface BoardService {

	List<BoardDTO> boardSelect() throws Exception;

//	String boardInsert(Map<String, Object> map);
	
//	String boardInsert(BoardDTO boardDTO);

	BoardDTO boardDetail(int idx) throws Exception;

	void boardInsert(BoardDTO boardDTO);





}
