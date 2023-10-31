package com.example.spring_jsp.worldcup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WorldCupMapper {
    void createTable();
    void dropTable();
    void save(WorldCupDTO worldCupDTO);
    int worldCupInsert(WorldCupDTO worldCupDTO);
    int worldCupImageInsert(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupSelect(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupImageSelect(WorldCupDTO worldCupDTO);
    WorldCupDTO worldCupProcSelect(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupProcImageSelect(WorldCupDTO worldCupDTO);
    int worldCupDelete(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> myWorldCupSelect(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> myWorldCupImageSelect(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupSearch(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> worldCupImageSearch(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> myWorldCupSearch(WorldCupDTO worldCupDTO);
    List<WorldCupDTO> myWorldCupImageSearch(WorldCupDTO worldCupDTO);
}
