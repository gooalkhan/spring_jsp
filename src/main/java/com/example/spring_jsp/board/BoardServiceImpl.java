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
	
	// 인서트가 성공적으로 완료 되면, affectRowCount의 값은 1이 되며,
	// affectRowCount의 값이 1이면, IDX를 문자열 형식으로 반환하고,
	// 그렇지 않으면 null을 반환함.
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
	
	@Override
	public List<BoardDTO> commentShow(int idx) throws Exception{
		return boardMapper.commentShow(idx);
	}
}
