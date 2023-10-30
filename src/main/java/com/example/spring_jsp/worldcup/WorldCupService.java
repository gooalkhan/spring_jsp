package com.example.spring_jsp.worldcup;

import java.util.List;

public interface WorldCupService {

	String worldCupInsert(WorldCupDTO worldCupDTO);

	String worldCupImageInsert(WorldCupDTO worldCupDTO);

	List<WorldCupDTO> worldCupSelect(WorldCupDTO worldCupDTO) throws Exception;

	List<WorldCupDTO> worldCupImageSelect(WorldCupDTO worldCupDTO) throws Exception;
	
	WorldCupDTO worldCupProcSelect(WorldCupDTO worldCupDTO) throws Exception;

	List<WorldCupDTO> worldCupProcImageSelect(WorldCupDTO worldCupDTO) throws Exception;

	boolean worldCupDelete(WorldCupDTO worldCupDTO);

	List<WorldCupDTO> myWorldCupSelect(WorldCupDTO worldCupDTO) throws Exception;

	List<WorldCupDTO> myWorldCupImageSelect(WorldCupDTO worldCupDTO) throws Exception;

	

}
