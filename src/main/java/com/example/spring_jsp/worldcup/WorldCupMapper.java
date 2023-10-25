package com.example.spring_jsp.worldcup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WorldCupMapper {
    void createTable();
    void dropTable();
    int worldCupInsert(WorldCupDTO worldCupDTO);
    int worldCupImageInsert(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupSelect(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupImageSelect(WorldCupDTO worldCupDTO);
}
