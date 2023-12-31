package com.example.spring_jsp.worldcup;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorldCupServiceImpl implements WorldCupService {
    private final WorldCupMapper worldCupMapper;
    
	@Override
	public String worldCupInsert(WorldCupDTO worldCupDTO) {
	int affectRowCount = this.worldCupMapper.worldCupInsert(worldCupDTO);
	if (affectRowCount == 1) {
		return String.valueOf(worldCupDTO.getIdx());
		}
		return null;
	}
	
	@Override
	public String worldCupImageInsert(WorldCupDTO worldCupDTO) {
	int affectRowCount = this.worldCupMapper.worldCupImageInsert(worldCupDTO);
	if (affectRowCount == 1) {
		return String.valueOf(worldCupDTO.getIdx());
		}
		return null;
	}
	
	@Override
	public List<WorldCupDTO> worldCupSelect(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.worldCupSelect(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> worldCupImageSelect(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.worldCupImageSelect(worldCupDTO);
	}
    
	@Override
	public WorldCupDTO worldCupProcSelect(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.worldCupProcSelect(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> worldCupProcImageSelect(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.worldCupProcImageSelect(worldCupDTO);
	}
	
	@Override
	public boolean worldCupDelete(WorldCupDTO worldCupDTO) {
		int affectRowCount = this.worldCupMapper.worldCupDelete(worldCupDTO);
		return affectRowCount == 1;
	}
	
	@Override
	public List<WorldCupDTO> myWorldCupSelect(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.myWorldCupSelect(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> myWorldCupImageSelect(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.myWorldCupImageSelect(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> worldCupSearch(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.worldCupSearch(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> worldCupImageSearch(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.worldCupImageSearch(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> myWorldCupSearch(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.myWorldCupSearch(worldCupDTO);
	}
	
	@Override
	public List<WorldCupDTO> myWorldCupImageSearch(WorldCupDTO worldCupDTO) throws Exception {
		return worldCupMapper.myWorldCupImageSearch(worldCupDTO);
	}
}
