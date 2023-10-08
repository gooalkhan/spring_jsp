package com.example.spring_jsp.member;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    final static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    public void printAll() {
        List<MemberDTO> memberDTO = memberMapper.getAll();
        for (MemberDTO m: memberDTO) {
            logger.info(m.getId() + " " + m.getName());
        }
    }
}
