package com.example.spring_jsp.member;

import java.util.List;
import java.util.Map;

public interface MemberService {

	List<MemberDTO> memberSelect() throws Exception;

	String memberJoin(Map<String, Object> map);
	
	MemberDTO memberDetail(String id) throws Exception;

	MemberDTO memberLogin(String id) throws Exception;

	void printAll();

	



	




}
