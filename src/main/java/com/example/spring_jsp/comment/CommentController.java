package com.example.spring_jsp.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {
	private final CommentService commentService;
	
	//댓글 쓰기
	@PostMapping("/commentInsert")
	public ModelAndView commentCreatePost(CommentDTO commentDTO) throws Exception {
		ModelAndView mav = new ModelAndView();
		String id = this.commentService.commentInsert(commentDTO);
	    if (id == null) {
	        mav.setViewName("redirect:/error");
	    }else {
	        mav.setViewName("redirect:/boardList");
	    } 
	    return mav;
	}

}