package com.example.spring_jsp.member;

import java.util.List;

public interface MemberService {

	void printAll();
	
	List<MemberDTO> memberSelect() throws Exception;
	
	String memberJoin(MemberJoinDTO memberJoinDTO);
	
	MemberDTO memberDetail(String id) throws Exception;

	MemberDTO memberLogin(MemberDTO memberDTO) throws Exception;

	boolean memberUpdate(MemberDTO memberDTO);

	boolean memberDelete(MemberDTO memberDTO);

	MemberDTO checkIdDuplication(String id) throws Exception;

	MemberDTO checkNameDuplication(String name) throws Exception;

	MemberDTO checkEmailDuplication(String email) throws Exception;

	boolean subadminAppoint(MemberDTO memberDTO);

	boolean subadminAppointCancel(MemberDTO memberDTO);








}
