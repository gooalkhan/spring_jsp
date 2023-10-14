package com.example.spring_jsp.config;

import com.example.spring_jsp.board.BoardDTO;
import com.example.spring_jsp.board.BoardMapper;
import com.example.spring_jsp.book.BookDTO;
import com.example.spring_jsp.book.BookMapper;
import com.example.spring_jsp.book.keyword.KeywordDTO;
import com.example.spring_jsp.book.keyword.KeywordMapper;
import com.example.spring_jsp.comment.CommentDTO;
import com.example.spring_jsp.comment.CommentMapper;
import com.example.spring_jsp.member.MemberDTO;
import com.example.spring_jsp.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@Component
public class DatabaseLoader implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final MemberMapper memberMapper;
    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;
    private final BookMapper bookMapper;
    private final KeywordMapper keywordMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run(String... args) {

        // 초기 테이블 생성
        if (activeProfile.equals("prod")) {
            commentMapper.dropTable();
            boardMapper.dropTable();
            memberMapper.dropTable();
            keywordMapper.dropTable();
            bookMapper.dropTable();
        }

        memberMapper.createTable();
        boardMapper.createTable();
        commentMapper.createTable();
        bookMapper.createTable();
        keywordMapper.createTable();

        // 초기 데이터 추가
        MemberDTO entity = new MemberDTO();
        entity.setId("hong");
        entity.setPw("1234");
        entity.setEmail("hong@gmail.com");
        entity.setName("홍길동");
        entity.setJoinDate(new Timestamp(System.currentTimeMillis()));
        memberMapper.save(entity);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setSubject("제목은");
        boardDTO.setContent("내용이다");
        boardDTO.setMembertbl_id("hong");
        boardMapper.boardInsert(boardDTO);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent("댓글이다");
        commentDTO.setMembertbl_id("hong");
        commentDTO.setBoardtbl_idx(1);
        commentMapper.save(commentDTO);

        initBooktbl();
        initKeywordtbl(); //booktbl 테이블 생성 전 실행해야 함(외래 키)
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        if (activeProfile.equals("prod")) {
            return null;
        } else {
            return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
        }
    }

    public void initBooktbl() {
        URL url = this.getClass().getResource("/sample_data/booktbl.csv");
        try {
            File file = new File(url.toURI());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line; String[] arr; BookDTO bookDTO; int counter = 0;
            while ((line = br.readLine()) != null) {
                counter++;
                arr = line.split("\\|");
                bookDTO = new BookDTO();
                bookDTO.setBookid(Long.parseLong(arr[0]));
                bookDTO.setTitle(arr[1]);
                bookDTO.setAuthor(arr[2]);
                bookDTO.setPublisher(arr[3]);
                bookDTO.setPublish_date(arr[4].equals("NULL")?null:java.sql.Date.valueOf(arr[4]));
                bookDTO.setReg_date(arr[5].equals("NULL")?null:dateFormat.parse(arr[5]));
                bookDTO.setCategory(Integer.parseInt(arr[6]));
                bookDTO.setReview_count(arr[7].equals("NULL")? null:Integer.parseInt(arr[7]));
                bookDTO.setPreference_count(arr[8].equals("NULL")?null:Integer.parseInt(arr[8]));
                bookDTO.setIs_complete(arr[9].equals("NULL")?null:arr[9].equals("1"));
                bookDTO.setIs_gidamu(arr[10].equals("NULL")?null:arr[10].equals("1"));
                bookDTO.setIs_adult_only(arr[11].equals("NULL")?null:arr[11].equals("1"));
                bookDTO.setLast_update(dateFormat.parse(arr[12]));

                bookMapper.bookInsert(bookDTO);
            }
            br.close();
            logger.info("booktbl inserted {} rows of sample data", counter);
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void initKeywordtbl() {
        URL url = this.getClass().getResource("/sample_data/keywordtbl.csv");
        try {
            File file = new File(url.toURI());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line; String[] arr; KeywordDTO keywordDTO; int counter = 0;
            while ((line = br.readLine()) != null) {
                arr = line.split("\\|");
                keywordDTO = new KeywordDTO();
                keywordDTO.setBookid(Long.parseLong(arr[1]));
                keywordDTO.setKeyword(arr[2]);
                keywordDTO.setLast_update(dateFormat.parse(arr[3]));

                keywordMapper.keywordInsert(keywordDTO);
                counter++;
            }
            logger.info("keywordtbl inserted {} rows of sample data", counter);
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}