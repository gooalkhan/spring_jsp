package com.example.spring_jsp.board;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> boardSelect() throws Exception {
		return boardMapper.boardSelect();
	}
	
//	@Override
//	public String boardInsert(Map<String, Object> map) {
//		int affectRowCount = this.boardMapper.boardInsert(map);
//		if (affectRowCount == 1) {
//			return map.get("id").toString();
//		}
//		return null;
//	}
	
//	@Override
//	public String boardInsert(BoardDTO boardDTO) {
//		int affectRowCount = this.boardMapper.boardInsert(boardDTO);
//		if (affectRowCount == 1) {
//			return String.valueOf(boardDTO.getIdx());
//		}
//		return null;
//	}
	@Override
	public void boardInsert(BoardDTO boardDTO) {
		boardMapper.boardInsert(boardDTO);
	}
	
	@Override
	public BoardDTO boardDetail(int idx) throws Exception {
		return boardMapper.boardDetail(idx);
	}
}
