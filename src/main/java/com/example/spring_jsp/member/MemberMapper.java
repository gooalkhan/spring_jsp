package com.example.spring_jsp.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {
    List<MemberDTO> getAll();

    void createTable();

    void save(MemberDTO memberDTO);

    void dropTable();
}
