package com.example.spring_jsp.comment;

import com.example.spring_jsp.comment.CommentDTO;
import com.example.spring_jsp.comment.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void printAll() {
        List<CommentDTO> commentDTO = commentMapper.getAll();
        for (CommentDTO c: commentDTO) {
            logger.info(c.getMembertbl_ID() + " " + c.getContent());
        }
    }
}
