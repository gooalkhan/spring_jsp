package com.example.spring_jsp.like;

public interface LikeService {

	void likeUpdate(int boardtbl_idx);

	void likeUpdateCancel(int boardtbl_idx);

	void likeInsert(LikeDTO likeDTO);

	void likeDelete(LikeDTO likeDTO);

	void likeUpdateCheck(LikeDTO likeDTO);

	void likeUpdateCheckCancel(LikeDTO likeDTO);

	int likeCheck(LikeDTO likeDTO);

}
