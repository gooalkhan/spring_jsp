package com.example.spring_jsp.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void printAll() {
        List<BoardDTO> boardDTO = boardMapper.boardSelect();
        for (BoardDTO b: boardDTO) {
            logger.info(b.getSubject() + " " + b.getContent());
        }
    }
    
	@Override
	public List<BoardDTO> boardSelect() throws Exception {
		return boardMapper.boardSelect();
	}
	
	@Override
	public String boardInsert(BoardDTO boardDTO) {
		int affectRowCount = this.boardMapper.boardInsert(boardDTO);
		if (affectRowCount == 1) {
			return String.valueOf(boardDTO.getIdx());
		}
		return null;
	}
	
	@Override
	public BoardDTO boardDetail(int idx) throws Exception {
		return boardMapper.boardDetail(idx);
	}
	
	@Override
	public boolean boardUpdate(BoardDTO boardDTO) {
		int affectRowCount = this.boardMapper.boardUpdate(boardDTO);
		return affectRowCount == 1;
	}
	
	@Override
	public boolean boardDelete(BoardDTO boardDTO) {
		int affectRowCount = this.boardMapper.boardDelete(boardDTO);
		return affectRowCount == 1;
	}
	
	@Override
	public List<BoardDTO> boardSearch(String subject) throws Exception{
		return boardMapper.boardSearch(subject);
	}
}
