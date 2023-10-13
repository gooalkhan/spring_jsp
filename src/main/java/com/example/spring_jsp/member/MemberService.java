package com.example.spring_jsp.member;

import java.util.List;

public interface MemberService {

	void printAll();
	
	List<MemberDTO> memberSelect() throws Exception;
	
	String memberJoin(MemberDTO memberDTO);
	
	MemberDTO memberDetail(String id) throws Exception;
	
//	이거 지우지 말고 흔적으로 남겨주세요.
//	MemberDTO memberLogin(String id) throws Exception;
	
	MemberDTO memberLogin(MemberDTO memberDTO) throws Exception;

	boolean memberUpdate(MemberDTO memberDTO);

	boolean memberDelete(MemberDTO memberDTO);

	
}
