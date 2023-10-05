package com.example.spring_jsp.config;

import com.example.spring_jsp.member.MemberVO;
import com.example.spring_jsp.mybatis.TestMapper;
import lombok.RequiredArgsConstructor;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.sql.SQLException;
import java.sql.Timestamp;
@RequiredArgsConstructor
@Component
public class DatabaseLoader implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final TestMapper testMapper;

    @Override
    public void run(String... args) {
        // 초기 테이블 생성
        if (activeProfile.equals("prod")) {
            testMapper.dropTable();
        }

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

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        if (activeProfile.equals("prod")) {
            return null;
        } else {
            return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
        }
    }
}