package com.example.spring_jsp.comment;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
	private final CommentMapper boardMapper;
	
	@Override
	public List<CommentDTO> boardSelect() throws Exception {
		return boardMapper.boardSelect();
	}
	
	@Override
	public String boardInsert(Map<String, Object> map) {
		int affectRowCount = this.boardMapper.boardInsert(map);
		if (affectRowCount == 1) {
			return map.get("id").toString();
		}
		return null;
	}
	
	@Override
	public CommentDTO boardDetail(int idx) throws Exception {
		return boardMapper.boardDetail(idx);
	}
}
