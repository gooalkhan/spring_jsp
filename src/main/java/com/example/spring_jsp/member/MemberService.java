package com.example.spring_jsp.member;

import com.example.spring_jsp.mybatis.TestMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final TestMapper testMapper;
    final static Logger logger = LoggerFactory.getLogger(MemberService.class);

    public void printAll() {
        List<MemberVO> memberVO = testMapper.getAll();
        for (MemberVO m: memberVO) {
            logger.info(m.getId() + " " + m.getName());
        }
    }


}
