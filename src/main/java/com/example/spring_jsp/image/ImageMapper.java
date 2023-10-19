package com.example.spring_jsp.image;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ImageMapper {
    void createTable();
    void dropTable();
}
