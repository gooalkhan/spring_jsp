package com.example.spring_jsp.board;

import com.example.spring_jsp.notification.NotificationService;
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
	private final NotificationService notificationService;
    
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

		String sid = boardDTO.getMembertbl_id();
		//notificationService 사용예시
		if (affectRowCount == 1) {
			notificationService.send(sid, "글 작성에 성공했습니다");
			return String.valueOf(boardDTO.getIdx());
		}
		return null;
	}
	
	@Override
	public BoardDTO boardDetail(BoardDTO boardDTO) throws Exception {
		return boardMapper.boardDetail(boardDTO);
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
	public List<BoardDTO> boardSearch(String subject) throws Exception {
		return boardMapper.boardSearch(subject);
	}
	
	@Override
	public List<BoardDTO> commentShow(BoardDTO boardDTO) throws Exception {
		return boardMapper.commentShow(boardDTO);
	}
	
	@Override
	public int boardView(int idx) {
		return this.boardMapper.boardView(idx);
	}
	
	@Override
	public BoardDTO boardDetailJoin(BoardDTO boardDTO) throws Exception {
		return boardMapper.boardDetailJoin(boardDTO);
	}
	
	@Override
	public List<BoardDTO> boardListJoin() throws Exception {
		return boardMapper.boardListJoin();
	}
	
	@Override
	public BoardDTO likeButton(BoardDTO boardDTO) throws Exception {
		return boardMapper.likeButton(boardDTO);
	}

	@Override
	public String imageUpload(BoardDTO boardDTO) {
		int affectRowCount = this.boardMapper.imageUpload(boardDTO);
		if (affectRowCount == 1) {
			return String.valueOf(boardDTO.getIdx());
		}
		return null;
	}
	
	@Override
	public List<BoardDTO> imageSelect(BoardDTO boardDTO) {
		return boardMapper.imageSelect(boardDTO);
	}
	
	@Override
	public boolean imageDelete(BoardDTO boardDTO) {
		int affectRowCount = this.boardMapper.imageDelete(boardDTO);
		return affectRowCount == 1;
	}
	
	@Override
	public List<BoardDTO> commentNum() {
		return boardMapper.commentNum();
	}
	
	@Override
	public List<BoardDTO> boardListJoinPop() {
		return boardMapper.boardListJoinPop();
	}
	
	@Override
	public List<BoardDTO> commentNumPop() {
		return boardMapper.commentNumPop();
	}
	
}
