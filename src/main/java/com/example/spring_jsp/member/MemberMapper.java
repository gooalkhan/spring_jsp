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
    
    //TODO : map으로 된 거 다 DTO로 고칠 예정
	List<MemberDTO> memberSelect();
	int memberJoin(MemberDTO memberDTO);
	int memberUpdate(MemberDTO memberDTO);
	int memberDelete(Map<String, Object> map);
	MemberDTO memberDetail(String id);
	MemberDTO memberLogin(String id);
}
