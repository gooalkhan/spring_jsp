package com.example.spring_jsp.board;

import com.example.spring_jsp.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    void createTable();

    void dropTable();

    List<BoardDTO> boardSelect();

    void boardInsert(BoardDTO boardDTO);

    void boardDelete(BoardDTO boardDTO);

    BoardDTO boardDetail(BoardDTO boardDTO);

}
