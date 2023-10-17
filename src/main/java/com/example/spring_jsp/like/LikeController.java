package com.example.spring_jsp.like;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LikeController {
	private final LikeService likeService;
	
	@PostMapping("/boardLike")
	public ModelAndView likeUpdate(LikeDTO likeDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int likeCheck = likeService.likeCheck(likeDTO);
		int boardtbl_idx = likeDTO.getBoardtbl_idx();
		if(likeCheck == 0) {
			//좋아요 처음누름
			likeService.likeInsert(likeDTO); //like테이블에 데이터 삽입
			likeService.likeUpdate(boardtbl_idx);	//게시판테이블에 like +1
			likeService.likeUpdateCheck(likeDTO);//like테이블 구분자 1
	    	request.setAttribute("msg", "추천이 완료되었습니다.");
	    	request.setAttribute("url", "/boardDetail?idx="+boardtbl_idx);
	        mav.setViewName("/alert");
		}else if(likeCheck == 1) {
			//좋아요 취소
			likeService.likeUpdateCheckCancel(likeDTO); //like테이블 구분자0
			likeService.likeUpdateCancel(boardtbl_idx); //게시판테이블에 like - 1
			likeService.likeDelete(likeDTO); //like테이블에 데이터 삭제
	    	request.setAttribute("msg", "추천이 취소되었습니다.");
	    	request.setAttribute("url", "/boardDetail?idx="+boardtbl_idx);
	        mav.setViewName("/alert");
		}
		return mav;
}
}
