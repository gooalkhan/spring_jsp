package com.example.spring_jsp.comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

	List<CommentDTO> boardSelect() throws Exception;

	String boardInsert(Map<String, Object> map);

	CommentDTO boardDetail(int idx) throws Exception;

}
