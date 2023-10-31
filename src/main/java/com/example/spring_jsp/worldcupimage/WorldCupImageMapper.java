package com.example.spring_jsp.worldcupimage;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WorldCupImageMapper {
    void createTable();
    void dropTable();
    void save(WorldCupImageDTO worldCupImageDTO);
}
