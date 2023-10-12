package com.example.spring_jsp.comment;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    List<CommentDTO> getAll();

    void createTable();

    void save(CommentDTO commentDTO);

    void dropTable();
    
    int commentInsert(CommentDTO commentDTO);
    
    
    
}
