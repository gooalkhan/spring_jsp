package com.example.spring_jsp.like;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService{
	
	private final LikeMapper likeMapper;
	
	@Override
	public void likeUpdate(int boardtbl_idx) {
		likeMapper.likeUpdate(boardtbl_idx);
	}
	
	@Override
	public void likeUpdateCancel(int boardtbl_idx) {
		likeMapper.likeUpdateCancel(boardtbl_idx);
	}
	
	@Override
	public void likeInsert(LikeDTO likeDTO) {
		likeMapper.likeInsert(likeDTO);
	}
	
	@Override
	public void likeDelete(LikeDTO likeDTO) {
		likeMapper.likeDelete(likeDTO);
	}
	
	@Override
	public void likeUpdateCheck(LikeDTO likeDTO) {
		likeMapper.likeUpdateCheck(likeDTO);
	}
	
	@Override
	public void likeUpdateCheckCancel(LikeDTO likeDTO) {
		likeMapper.likeUpdateCheckCancel(likeDTO);
	}
	
	@Override
	public int likeCheck(LikeDTO likeDTO) {
		return likeMapper.likeCheck(likeDTO);
	}
}
