package com.example.spring_jsp.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService boardService;
	
	//게시글 목록
	@GetMapping("/boardList")
	public ModelAndView boardList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> list = boardService.boardSelect();
		mav.addObject("data", list);
		mav.setViewName("/board/boardList");
		return mav;
	}
	
	//게시글 쓰기 페이지
	@GetMapping("/boardInsert")
	public String boardInsert() {
		return "/board/boardInsert";
	}
	
	//게시글 쓰기 처리
	/*
	ServiceImpl에서 짰던 것 처럼, id가 null이라면(insert가 성공적으로 이루어지지 않았다면)
	boardInsert로 리다이렉트 되고
	null이 아니라 값이 있다면
	boardList로 리다이렉트됨
	*/
	@PostMapping("/boardInsertPost")
	public ModelAndView boardInsertPost(BoardDTO boardDTO) {
		ModelAndView mav = new ModelAndView();
		String id = this.boardService.boardInsert(boardDTO);
		if (id == null) {
			mav.setViewName("redirect:/boardInsert");
		}else {
			mav.setViewName("redirect:/boardList");
		}
		return mav;
	}
	
	//게시글 상세 정보
	/*
	DTO는 글 상세정보를 보기 위함이고, CDTO는 댓글들을 보기 위함
	각각을 view에서 활용해주기 위해 mav.addObject로 key와 value를 담아서 보내줌
	*/
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(int idx) throws Exception{
		ModelAndView mav = new ModelAndView();
		BoardDTO DTO = boardService.boardDetail(idx);
		List<BoardDTO> CDTO = boardService.commentShow(idx);
		mav.addObject("data", DTO);
		mav.addObject("show", CDTO);
		mav.setViewName("/board/boardDetail");
		return mav;
	}
	
	//게시글 수정 페이지
	@GetMapping("/boardUpdate")
	public ModelAndView boardUpdate(int idx) throws Exception{
		ModelAndView mav = new ModelAndView();
		BoardDTO DTO = this.boardService.boardDetail(idx);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardUpdate");
		return mav;	
	}
	
	//게시글 수정 처리
	@PostMapping("/boardUpdate")
	public ModelAndView boardUpdatePost(BoardDTO boardDTO) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = this.boardService.boardUpdate(boardDTO);
		if(isUpdateSuccess) {
			int idx = boardDTO.getIdx();
			mav.setViewName("redirect:/boardDetail?idx="+idx);
		} else {
			mav.setViewName("/error");
		}
		return mav;
	}
	
	//게시글 삭제 처리
	@PostMapping("/boardDelete")
	public ModelAndView boardDeletePost(BoardDTO boardDTO) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = this.boardService.boardDelete(boardDTO);
		//TODO: 댓글이 달려있으면 게시물이 지워지지 않으므로 cascasde로 처리하던 해야함
		if(isDeleteSuccess) {
			
			mav.setViewName("redirect:/boardList");
		} else {
			int idx = boardDTO.getIdx();
			mav.setViewName("redirect:/boardDetail?idx="+idx);
		}
		return mav;
	}
	
	//게시글 찾기
	@GetMapping("/boardSearch")
	public ModelAndView boardSearch(String subject) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> DTO = this.boardService.boardSearch(subject);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardList");
		return mav;
	}
}
