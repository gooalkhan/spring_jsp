package com.example.spring_jsp.member;

import java.util.List;

public interface MemberService {

	void printAll();
	
	List<MemberDTO> memberSelect() throws Exception;
	
	String memberJoin(MemberDTO memberDTO);
	
	MemberDTO memberDetail(String id) throws Exception;

	MemberDTO memberLogin(String id) throws Exception;

	boolean memberUpdate(MemberDTO memberDTO);
}
