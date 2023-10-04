package com.example.spring_jsp.config;

import com.example.spring_jsp.member.MemberVO;
import com.example.spring_jsp.mybatis.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@RequiredArgsConstructor
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final TestMapper testMapper;

    @Override
    public void run(String... args) throws Exception {
        // 초기 테이블 생성
        testMapper.createTable();

        // 초기 데이터 추가
        MemberVO entity = new MemberVO();
        entity.setId("hong");
        entity.setPw("1234");
        entity.setEmail("hong@gmail.com");
        entity.setName("홍길동");
        entity.setJoindate(new Timestamp(System.currentTimeMillis()));
        testMapper.save(entity);
    }
}