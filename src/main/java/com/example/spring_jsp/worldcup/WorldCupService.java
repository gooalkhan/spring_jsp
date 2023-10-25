package com.example.spring_jsp.worldcup;

import java.util.List;

public interface WorldCupService {

	String worldCupInsert(WorldCupDTO worldCupDTO);

	String worldCupImageInsert(WorldCupDTO worldCupDTO);

	List<WorldCupDTO> worldCupSelect(WorldCupDTO worldCupDTO) throws Exception;

	List<WorldCupDTO> worldCupImageSelect(WorldCupDTO worldCupDTO) throws Exception;

}
