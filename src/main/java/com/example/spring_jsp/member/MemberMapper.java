package com.example.spring_jsp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {
    List<MemberDTO> getAll();

    void createTable();

    void save(MemberDTO memberDTO);

    void dropTable();
    
	List<MemberDTO> memberSelect();
	int memberJoin(MemberJoinDTO memberJoinDTO);
	int memberUpdate(MemberDTO memberDTO);
	int memberDelete(MemberDTO memberDTO);
	MemberDTO memberDetail(String id);
	MemberDTO memberLogin(MemberDTO memberDTO);
	MemberDTO existsById(String id);
	MemberDTO existsByName(String name);
	MemberDTO existsByEmail(String email);
	int subadminAppoint(MemberDTO memberDTO);
	int subadminAppointCancel(MemberDTO memberDTO);
	MemberDTO whereIsMyId(MemberDTO memberDTO);
	MemberDTO whereIsMyPw(MemberDTO memberDTO);
	int resetPw(MemberDTO memberDTO);
}
