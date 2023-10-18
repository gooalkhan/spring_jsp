package com.example.spring_jsp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
	public ModelAndView memberJoinPost(@Valid MemberJoinDTO memberJoinDTO, BindingResult bindingResult, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		/* 검증 */
		if(bindingResult.hasErrors()) {
			/* 회원가입 실패 시 입력했던 데이터 값 유지 */
			mav.addObject("userDTO", memberJoinDTO);
			
			/* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
			List<FieldError> list = bindingResult.getFieldErrors();
			Map<String, String> errorMsg = new HashMap<>();
			
			for(int i=0;i<list.size();i++) {
				String field = list.get(i).getField(); 
				String message = list.get(i).getDefaultMessage(); 
				
				System.out.println("필드 = " + field);
				System.out.println("메세지 = " +message);
				
				errorMsg.put(field, message);
			}
			/* 에러 메시지 데이터 저장 */
			mav.addObject("errorMsg", errorMsg);
			
			/* 회원가입 페이지로 리턴 */
			mav.setViewName("/member/memberJoin");
//		String id = this.memberService.memberJoin(memberDTO);
		}else {
			String id = memberJoinDTO.getId();
			String name = memberJoinDTO.getName();
			String email = memberJoinDTO.getEmail();
			MemberDTO eId = this.memberService.checkIdDuplication(id);
			MemberDTO eName = this.memberService.checkNameDuplication(name);
			MemberDTO eEmail = this.memberService.checkEmailDuplication(email);
				/* 아이디 중복 검사 */
			if(eId != null) {
		    	request.setAttribute("msg", "중복된 아이디입니다. 다시 아이디를 입력해주세요.");
		    	request.setAttribute("url", "/memberJoin");
				mav.setViewName("/alert");
				/* 이름 중복 검사 */
			}else if(eName != null){
		    	request.setAttribute("msg", "중복된 이름입니다. 다시 이름을 입력해주세요.");
		    	request.setAttribute("url", "/memberJoin");
				mav.setViewName("/alert");
				/* 아메일 중복 검사 */
			}else if(eEmail != null) {
		    	request.setAttribute("msg", "중복된 이메일입니다. 다시 이메일을 입력해주세요.");
		    	request.setAttribute("url", "/memberJoin");
				mav.setViewName("/alert");
			}
			/* 회원가입 성공 시 */
			else {
				this.memberService.memberJoin(memberJoinDTO);
		    	request.setAttribute("msg", "회원가입이 완료되었습니다.");
		    	request.setAttribute("url", "/");
				mav.setViewName("/alert");
			}
		}
		return mav;
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
	
	//회원 로그인 처리
	//세션에 저장해줌
	@PostMapping("/memberLogin")
	public ModelAndView memberLoginPost(MemberDTO memberDTO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		MemberDTO DTO = memberService.memberLogin(memberDTO);
		if(DTO == null) {
			request.setAttribute("msg", "아이디와 비밀번호가 틀렸습니다. 다시 입력해주세요.");
			request.setAttribute("url", "/memberLogin");
			mav.setViewName("/alert");
		}
		else {
		mav.addObject("data", DTO);
		String id = DTO.getId();
		String pw = DTO.getPw();
		String name = DTO.getName();
		String admin = DTO.getAdmin();
		HttpSession session = request.getSession();
		session.setAttribute("sid", id);
		session.setAttribute("spw", pw);
		session.setAttribute("sname", name);
		session.setAttribute("sadmin", admin);
		mav.setViewName("/index");
		}
		return mav;
	}
	
	//회원 로그아웃 처리
	//세션을 없애버림
	@PostMapping("/memberLogout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index";
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
	public ModelAndView memberDeletePost(MemberDTO memberDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean isDeleteSuccess = this.memberService.memberDelete(memberDTO);
		if(isDeleteSuccess) {
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("msg", "회원 탈퇴에 성공했습니다. 지금까지 이용해주셔서 감사합니다.");
			request.setAttribute("url", "/");
			mav.setViewName("/alert");
		}else {
			String id = memberDTO.getId();
			request.setAttribute("msg", "회원 탈퇴에 실패했습니다. 다시 시도해주세요.");
			request.setAttribute("url", "/memberDetail?id=" + id);
			mav.setViewName("/alert");
		}
		return mav;
	}
	
	//부관리자 임명과 박탈
	@PostMapping("/subadminAppoint")
	public ModelAndView subadminAppoint(MemberDTO memberDTO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String admin = memberDTO.getAdmin();
		if(admin.equals("subadmin")) {
			boolean isUpdateSuccess = this.memberService.subadminAppointCancel(memberDTO);
			if(isUpdateSuccess) {
				String id = memberDTO.getId();
				request.setAttribute("msg", "부관리자 박탈에 성공했습니다.");
				request.setAttribute("url", "/memberUpdate?id="+id);
				mav.setViewName("/alert");
			} else {
				String id = memberDTO.getId();
				request.setAttribute("msg", "부관리자 박탈에 실패했습니다. 다시 시도해주세요.");
				request.setAttribute("url", "/memberUpdate?id="+id);
				mav.setViewName("/alert");
			}
		}else if(admin.equals("admin")) {
			String id = memberDTO.getId();
			request.setAttribute("msg", "잘못된 요청입니다.");
			request.setAttribute("url", "/memberUpdate?id="+id);
			mav.setViewName("/alert");
		}else {
			boolean isUpdateSuccess = this.memberService.subadminAppoint(memberDTO);
			if(isUpdateSuccess) {
				String id = memberDTO.getId();
				request.setAttribute("msg", "부관리자 임명에 성공했습니다.");
				request.setAttribute("url", "/memberUpdate?id="+id);
				mav.setViewName("/alert");
			} else {
				String id = memberDTO.getId();
				request.setAttribute("msg", "부관리자 임명에 실패했습니다. 다시 시도해주세요.");
				request.setAttribute("url", "/memberUpdate?id="+id);
				mav.setViewName("/alert");
			}
		}
		return mav;
	}
	
}
