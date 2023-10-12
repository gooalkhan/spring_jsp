package com.example.spring_jsp.comment;

import java.util.List;

public interface CommentService {

	void printAll();

	String commentInsert(CommentDTO commentDTO);

}
