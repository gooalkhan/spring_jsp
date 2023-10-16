package com.example.spring_jsp.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {
	private final CommentService commentService;
	
	//댓글 쓰기
	@PostMapping("/commentInsert")
	public ModelAndView commentCreatePost(CommentDTO commentDTO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
	    if (session.getAttribute("sid") == null) {
	    	request.setAttribute("msg", "로그인 시 작성 가능합니다. 로그인 해주세요.");
	    	request.setAttribute("url", "/memberLogin");
	        mav.setViewName("/alert");
	    }else {
	    	this.commentService.commentInsert(commentDTO);
	    	int boardtbl_idx = commentDTO.getBoardtbl_idx();
	        mav.setViewName("redirect:/boardDetail?idx="+boardtbl_idx);
	    } 
	    return mav;
	}
	
	//댓글 삭제
	@PostMapping("/commentDelete")
	public ModelAndView memberDeletePost(CommentDTO commentDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean isDeleteSuccess = this.commentService.commentDelete(commentDTO);
		if(isDeleteSuccess) {
			int bidx = commentDTO.getBidx();
			mav.setViewName("redirect:/boardDetail?idx="+bidx);
		}else {
			//댓글 삭제 실패 시, 알람창이 뜸.
			int bidx = commentDTO.getBidx();
	    	request.setAttribute("msg", "댓글 삭제를 실패했습니다. 다시 시도해주세요.");
	    	request.setAttribute("url", "/boardDetail?idx="+bidx);
			mav.setViewName("/alert");
		}
		return mav;
	}

}