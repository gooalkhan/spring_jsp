package com.example.spring_jsp.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	private final MemberService memberService;
	
	//회원 목록
	@GetMapping("/memberList")
	public ModelAndView memberList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> list = memberService.memberSelect();
		mav.addObject("data", list);
		mav.setViewName("/member/memberList");
		return mav;
	}
	
	//회원 가입 페이지
	@GetMapping("/memberJoin")
	public String memberJoin() {
		return "/member/memberJoin";
	}
	
	//회원 가입 처리
	@PostMapping("/memberJoin")
	public ModelAndView memberJoinPost(MemberDTO memberDTO) {
		ModelAndView mav = new ModelAndView();
		String id = this.memberService.memberJoin(memberDTO);
		if (id == null) {
			mav.setViewName("redirect:/memberJoin");
		}else {
			mav.setViewName("redirect:/memberJoinSuccess");
		}
		return mav;
	}
	
	//회원 가입 성공 시 이 페이지로 이동
	@GetMapping("/memberJoinSuccess")
	public String memberJoinSuccess() {
		return "/member/memberJoinSuccess";
	}
	
	//회원 정보
	@GetMapping("/memberDetail")
	public ModelAndView memberDetail(String id) throws Exception{
		ModelAndView mav = new ModelAndView();
		MemberDTO DTO = memberService.memberDetail(id);
		mav.addObject("data", DTO);
		mav.setViewName("/member/memberDetail");
		return mav;
	}
	
	//회원 로그인 페이지
	@GetMapping("/memberLogin")
	public String login() {
		return "/member/memberLogin";
	}
	
//	이거 지우지 말고 흔적으로 남겨주세요.
//	@PostMapping("/memberLogin")
//	public ModelAndView memberLoginPost(HttpServletRequest request) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		MemberDTO DTO = memberService.memberLogin(request.getParameter("id"));
//		mav.addObject("data", DTO);
//		String id = DTO.getId();
//		String pw = DTO.getPw();
//		String name = DTO.getName();
//		HttpSession Session = request.getSession();
//		Session.setAttribute("sid", id);
//		Session.setAttribute("spw", pw);
//		Session.setAttribute("sname", name);
//		
//		mav.setViewName("/indexTEMP");
//		return mav;
//	}
	
	//회원 로그인 처리
	//세션에 저장해줌
	@PostMapping("/memberLogin")
	public ModelAndView memberLoginPost(MemberDTO memberDTO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		MemberDTO DTO = memberService.memberLogin(memberDTO);
		mav.addObject("data", DTO);
		String id = DTO.getId();
		String pw = DTO.getPw();
		String name = DTO.getName();
		HttpSession Session = request.getSession();
		Session.setAttribute("sid", id);
		Session.setAttribute("spw", pw);
		Session.setAttribute("sname", name);
		
		mav.setViewName("/indexTEMP");
		return mav;
	}
	
	//회원 로그아웃 처리
	//세션을 없애버림
	@PostMapping("/memberLogout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "/indexTEMP";
	}
	
	//회원 정보 수정 페이지
	@GetMapping("/memberUpdate")
	public ModelAndView memberUpdate(String id) throws Exception{
		ModelAndView mav = new ModelAndView();
		MemberDTO DTO = this.memberService.memberDetail(id);
		mav.addObject("data", DTO);
		mav.setViewName("/member/memberUpdate");
		return mav;	
	}
	
	//회원 정보 수정 처리
	@PostMapping("/memberUpdate")
	public ModelAndView memberUpdatePost(MemberDTO memberDTO) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = this.memberService.memberUpdate(memberDTO);
		if(isUpdateSuccess) {
			String id = memberDTO.getId();
			mav.setViewName("redirect:/memberDetail?id="+id);
		} else {
			mav.setViewName("/error");
		}
		return mav;
	}
	
	//회원 찾기
	@GetMapping("/memberSearch")
	public String memberSearch() {
		return "/member/memberSearch";
	}
	
	//회원 삭제
	@PostMapping("/memberDelete")
	public ModelAndView memberDeletePost(MemberDTO memberDTO) {
		ModelAndView mav = new ModelAndView();
		boolean isDeleteSuccess = this.memberService.memberDelete(memberDTO);
		if(isDeleteSuccess) {
			mav.setViewName("/member/memberDeleteSuccess");
		}else {
			String id = memberDTO.getId();
			mav.setViewName("redirect:/memberDetail?id=" + id);
		}
		return mav;
	}
	
}
