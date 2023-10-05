package com.example.spring_jsp.mybatis;

import com.example.spring_jsp.member.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<MemberVO> getAll();

    void createTable();

    void save(MemberVO memberVO);

    void dropTable();
}
