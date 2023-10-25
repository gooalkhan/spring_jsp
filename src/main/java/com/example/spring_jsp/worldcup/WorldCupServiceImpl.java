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
    
}
