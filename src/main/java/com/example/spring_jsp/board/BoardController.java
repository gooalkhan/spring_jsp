package com.example.spring_jsp.board;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
//	@PostMapping("/boardInsertPost")
//	public ModelAndView boardInsertPost(@RequestParam Map<String, Object> map) {
//		ModelAndView mav = new ModelAndView();
//		String id = this.boardService.boardInsert(map);
//		if (id == null) {
//			mav.setViewName("redirect:/boardInsert");
//		}else {
//			mav.setViewName("redirect:/boardList");
//		}
//		return mav;
//	}
	
//	@PostMapping("/boardInsertPost")
//	public ModelAndView boardInsertPost(BoardDTO boardDTO) {
//		ModelAndView mav = new ModelAndView();
//		String id = this.boardService.boardInsert(boardDTO);
//		if (id == null) {
//			mav.setViewName("redirect:/boardInsert");
//		}else {
//			mav.setViewName("redirect:/boardList");
//		}
//		return mav;
//	}
	
	@PostMapping("/boardInsertPost")
	public ModelAndView boardInsertPost(BoardDTO boardDTO) {
		boardService.boardInsert(boardDTO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/boardList");
		return mav;
	}
	
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(int idx) throws Exception{
		ModelAndView mav = new ModelAndView();
		BoardDTO DTO = boardService.boardDetail(idx);
		mav.addObject("data", DTO);
		mav.setViewName("/board/boardDetail");
		return mav;
	}
}