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
	    	int boardtbl_idx = commentDTO.getBoardtbl_idx();
	        mav.setViewName("redirect:/boardDetail?idx="+boardtbl_idx);
	    } 
	    return mav;
	}
	
	//댓글 삭제
	@PostMapping("/commentDelete")
	public ModelAndView memberDeletePost(CommentDTO commentDTO) {
		ModelAndView mav = new ModelAndView();
		boolean isDeleteSuccess = this.commentService.commentDelete(commentDTO);
		if(isDeleteSuccess) {
			int bidx = commentDTO.getBidx();
			mav.setViewName("redirect:/boardDetail?idx="+bidx);
		}else {
			//TODO : 삭제에 실패하면 어디로 가게 할지 생각해보기 일단 삭제가 실패하면 게시글목록으로 이동
			mav.setViewName("redirect:/boardList");
		}
		return mav;
	}

}