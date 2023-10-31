package com.example.spring_jsp.config;

import com.example.spring_jsp.analysis.python.PythonMapper;
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
import com.example.spring_jsp.like.LikeMapper;
import com.example.spring_jsp.image.ImageMapper;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingMapper;
import com.example.spring_jsp.worldcup.WorldCupMapper;
import com.example.spring_jsp.worldcupimage.WorldCupImageMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
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
    private final LikeMapper likeMapper;
    private final BookkeepingMapper bookkeepingMapper;
    private final ImageMapper imageMapper;
    private final PythonMapper pythonMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final WorldCupMapper worldCupMapper;
    private final WorldCupImageMapper worldCupImageMapper;
    
    @Override
    public void run(String... args) {
        // 회원 샘플 데이터 추가(일반 회원 홍길동 복사)
        String[] id = {"apple", "banana", "grapes", "orange", "strawberry", "watermelon", "cherry", "pineapple", "kiwi", "lemon", "lime", "peach", "mango", "grape", "pear", "blueberry", "raspberry"};
        String[] name = {"사과", "바나나", "포도", "오렌지", "딸기", "수박", "체리", "파인애플", "키위", "레몬", "라임", "복숭아", "망고", "포도", "배", "블루베리", "라즈베리"};
        
        // 초기 테이블 생성
        if (activeProfile.equals("prod")) {
        	worldCupImageMapper.dropTable();
        	worldCupMapper.dropTable();
            pythonMapper.dropTable();
        	imageMapper.dropTable();
            bookkeepingMapper.dropTable();
        	likeMapper.dropTable();
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
        likeMapper.createTable();
        bookkeepingMapper.createTable();
        imageMapper.createTable();
        pythonMapper.createTable();
        worldCupMapper.createTable();
        worldCupImageMapper.createTable();
        
        // 회원 샘플 데이터 추가(관리자 홍길동)
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("hong");
        // Hong!1234
        memberDTO.setPw("c137843278ae01068a144cfe4ef9e39f49092a1a74fb424d6e82a10d232f6c8a");
        memberDTO.setEmail("hong@example.com");
        memberDTO.setName("홍길동");
        memberDTO.setJoinDate(new Timestamp(System.currentTimeMillis()));
        memberDTO.setAdmin("admin");
        memberMapper.save(memberDTO);
        
        // 회원 샘플 데이터 추가(부관리자 테스트)
        memberDTO.setId("test");
        // Hong!1234
        memberDTO.setPw("c137843278ae01068a144cfe4ef9e39f49092a1a74fb424d6e82a10d232f6c8a");
        memberDTO.setEmail("test@example.com");
        memberDTO.setName("테스트");
        memberDTO.setJoinDate(new Timestamp(System.currentTimeMillis()));
        memberDTO.setAdmin("subadmin");
        memberMapper.save(memberDTO);
        
        
        for(int i = 0; i < 17; i++) {
            MemberDTO memberDTODupl = new MemberDTO();
            memberDTODupl.setId(id[i]);
            // Hong!1234
            memberDTODupl.setPw("c137843278ae01068a144cfe4ef9e39f49092a1a74fb424d6e82a10d232f6c8a");
            memberDTODupl.setEmail(id[i] + "@example.com");
            memberDTODupl.setName(name[i]);
            memberDTODupl.setJoinDate(new Timestamp(System.currentTimeMillis()));
            memberMapper.save(memberDTODupl);
        }

        // 게시글 샘플 데이터 추가(관리자 홍길동)
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setSubject("제목은");
        boardDTO.setContent("내용이다");
        boardDTO.setMembertbl_id("hong");
        boardMapper.boardInsert(boardDTO);
        
        // 게시글 샘플 데이터 추가(일반 회원 홍길동 복사)
        for(int i = 0; i < 17; i++) {
            BoardDTO boardDTODupl = new BoardDTO();
            boardDTODupl.setSubject(name[i] + " 이(가) 쓴 글 입니다.");
            boardDTODupl.setContent(name[i] + " 이(가) 쓴 내용 입니다.");
            boardDTODupl.setMembertbl_id(id[i]);
            boardMapper.boardInsert(boardDTODupl);
        }
        
        for(int i = 1; i <= 18; i++) {
        	// 댓글 샘플 데이터 추가(관리자 홍길동)
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent("댓글이다");
            commentDTO.setMembertbl_id("hong");
            commentDTO.setBoardtbl_idx(i);
            commentMapper.save(commentDTO);
        	for (int j = 0; j < 17; j++) {
        		// 댓글 샘플 데이터 추가(일반 회원 홍길동 복사)
                CommentDTO commentDTODupl = new CommentDTO();
                commentDTODupl.setContent(name[j] + " 가(이) 쓴 댓글입니다.");
                commentDTODupl.setMembertbl_id(id[j]);
                commentDTODupl.setBoardtbl_idx(i);
                commentMapper.save(commentDTODupl);
        	}
        }
        


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
        URL url = this.getClass().getResource("/sample_data/booktbl2.csv");
        try {
            File file = new File(url.toURI());
            // 책 소개 내용에 콤마, 큰따옴표가 포함되어 있어서 구분자를 |로 설정
            CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(parser).build();

            String[] arr; BookDTO bookDTO; int counter = 0;
            while ((arr = reader.readNext()) != null) {
                counter++;
                bookDTO = new BookDTO();
                bookDTO.setBookid(Long.parseLong(arr[0]));
                bookDTO.setTitle(arr[1]);
                bookDTO.setAuthor(arr[2]);
                bookDTO.setPublisher(arr[3]);
                bookDTO.setPublish_date(arr[4].equals("NULL")?null:java.sql.Date.valueOf(arr[4]));
                bookDTO.setReg_date(arr[5].equals("NULL")?null:dateFormat.parse(arr[5]));
                bookDTO.setCategory(arr[6]);
                bookDTO.setReview_count(arr[7].equals("NULL")? null:Integer.parseInt(arr[7]));
                bookDTO.setPreference_count(arr[8].equals("NULL")?null:Integer.parseInt(arr[8]));
                bookDTO.setSeries_count(arr[9].equals("NULL")?null:Integer.parseInt(arr[9]));
                bookDTO.setDescription(arr[10]);
                bookDTO.setIs_complete(arr[11].equals("NULL")?null:arr[11].equals("1")); // 1이면 true, 0이면 false
                bookDTO.setIs_gidamu(arr[12].equals("NULL")?null:arr[12].equals("1"));
                bookDTO.setIs_adult_only(arr[13].equals("NULL")?null:arr[13].equals("1"));
                bookDTO.setSeries_last_update(dateFormat.parse(arr[14]));

                bookMapper.bookInsert(bookDTO);
            }
            reader.close();
            logger.info("booktbl inserted {} rows of sample data", counter);
        } catch (URISyntaxException | IOException | ParseException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public void initKeywordtbl() {
        URL url = this.getClass().getResource("/sample_data/keywordtbl2.csv");
        try {
            File file = new File(url.toURI());
            CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(parser).build();

            String[] arr; KeywordDTO keywordDTO; int counter = 0;
            while ((arr = reader.readNext()) != null) {
                keywordDTO = new KeywordDTO();
                keywordDTO.setBookid(Long.parseLong(arr[1]));
                keywordDTO.setKeyword(arr[2]);
                keywordDTO.setLast_update(dateFormat.parse(arr[3]));

                keywordMapper.keywordInsert(keywordDTO);
                counter++;
            }
            reader.close();
            logger.info("keywordtbl inserted {} rows of sample data", counter);
        } catch (URISyntaxException | IOException | ParseException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}