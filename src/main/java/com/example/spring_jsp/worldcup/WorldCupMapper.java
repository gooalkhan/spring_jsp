package com.example.spring_jsp.worldcup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WorldCupMapper {
    void createTable();
    void dropTable();
}
