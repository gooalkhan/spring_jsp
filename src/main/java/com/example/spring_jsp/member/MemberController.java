package com.example.spring_jsp.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/memberList")
	public ModelAndView memberList() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<MemberDTO> list = memberService.memberSelect();
		mav.addObject("data", list);
		mav.setViewName("/member/memberList");
		return mav;
	}
	
	@GetMapping("/memberJoin")
	public String memberJoin() {
		return "/member/memberJoin";
	}
	
	@PostMapping("/memberJoin")
	public ModelAndView memberJoinPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		String id = this.memberService.memberJoin(map);
		if (id == null) {
			mav.setViewName("redirect:/memberJoin");
		}else {
			mav.setViewName("redirect:/memberJoinSuccess");
		}
		return mav;
	}
	
	@GetMapping("/memberJoinSuccess")
	public String memberJoinSuccess() {
		return "/member/memberJoinSuccess";
	}
	
	@GetMapping("/memberDetail")
	public ModelAndView memberDetail(String id) throws Exception{
		ModelAndView mav = new ModelAndView();
		MemberDTO DTO = memberService.memberDetail(id);
		mav.addObject("data", DTO);
		mav.setViewName("/member/memberDetail");
		return mav;
	}
	
	@GetMapping("/memberLogin")
	public String login() {
		return "/member/memberLogin";
	}
	
	@PostMapping("/memberLogin")
	public ModelAndView memberLoginPost(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		MemberDTO DTO = memberService.memberLogin(request.getParameter("id"));
		mav.addObject("data", DTO);
		String idx = String.valueOf(DTO.getIdx());
		String id = DTO.getId();
		String pw = DTO.getPw();
		String name = DTO.getName();
		HttpSession Session = request.getSession();
		Session.setAttribute("sidx", idx);
		Session.setAttribute("sid", id);
		Session.setAttribute("spw", pw);
		Session.setAttribute("sname", name);
		
		mav.setViewName("/indexTEMP");
		return mav;
	}
	
	@PostMapping("/memberLogout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "/indexTEMP";
	}
	
}