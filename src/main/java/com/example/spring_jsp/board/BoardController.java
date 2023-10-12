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
	
	@GetMapping("/boardList")
	public ModelAndView boardList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> list = boardService.boardSelect();
		mav.addObject("data", list);
		mav.setViewName("/board/boardList");
		return mav;
	}
	
	@GetMapping("/boardInsert")
	public String boardInsert() {
		return "/board/boardInsert";
	}
	
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
	
	@GetMapping("/boardUpdate")
	public ModelAndView boardUpdate(int idx) throws Exception{
		ModelAndView mav = new ModelAndView();
		BoardDTO DTO = this.boardService.boardDetail(idx);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardUpdate");
		return mav;	
	}
	
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
	
	@PostMapping("/boardDelete")
	public ModelAndView boardDeletePost(BoardDTO boardDTO) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = this.boardService.boardDelete(boardDTO);
		if(isDeleteSuccess) {
			
			mav.setViewName("redirect:/boardList");
		} else {
			int idx = boardDTO.getIdx();
			mav.setViewName("redirect:/boardDetail?idx="+idx);
		}
		return mav;
	}
	
	@GetMapping("/boardSearch")
	public ModelAndView boardSearch(String subject) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> DTO = this.boardService.boardSearch(subject);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardList");
		return mav;
	}
}
