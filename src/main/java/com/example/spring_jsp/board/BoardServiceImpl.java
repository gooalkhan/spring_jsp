package com.example.spring_jsp.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void printAll() {
        List<BoardDTO> boardDTO = boardMapper.boardSelect();
        for (BoardDTO b: boardDTO) {
            logger.info(b.getSubject() + " " + b.getContent());
        }
    }
}
