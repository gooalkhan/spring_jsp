package com.example.spring_jsp.member;

import java.util.List;
import java.util.Map;

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
	int memberJoin(Map<String, Object> map);
	int memberUpdate(Map<String, Object> map);
	int memberDelete(Map<String, Object> map);
	MemberDTO memberDetail(String id);
	MemberDTO memberLogin(String id);
	
}
