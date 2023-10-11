package com.example.spring_jsp.comment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void printAll() {
        List<CommentDTO> commentDTO = commentMapper.getAll();
        for (CommentDTO c: commentDTO) {
            logger.info(c.getMembertbl_id() + " " + c.getContent());
        }
    }
}
