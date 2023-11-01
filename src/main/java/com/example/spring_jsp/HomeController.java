package com.example.spring_jsp;

import com.example.spring_jsp.board.BoardDTO;
import com.example.spring_jsp.board.BoardService;
import com.example.spring_jsp.book.BookDTO;
import com.example.spring_jsp.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BoardService boardService;
    private final BookService bookService;

    @GetMapping("/")
    public String index(Model model) throws Exception {
        log.info("welcome home");
        log.info("os: " + System.getProperty("os.name"));
        log.info("user: " + System.getProperty("user.name"));

        List<BoardDTO> boardList = boardService.boardListJoin();

        if (boardList.size() > 10) {
            boardList = boardList.subList(0, 10);
        }
        
        List<BoardDTO> Clist = boardService.commentNum();
        //리스트를 역순으로
        Collections.reverse(Clist);
        
        if (Clist.size() > 10) {
        	Clist = Clist.subList(0, 10);
        }
        model.addAttribute("boardList", boardList);
        model.addAttribute("cnum", Clist);
        
        
        // 좋아요가 10 이상인 글들만 나오도록 하는 인기 게시글
        List<BoardDTO> boardListPop = boardService.boardListJoinPop();

        if (boardListPop.size() > 10) {
        	boardListPop = boardListPop.subList(0, 10);
        }
        
        List<BoardDTO> ClistPop = boardService.commentNumPop();
        //리스트를 역순으로
        Collections.reverse(ClistPop);
        
        if (ClistPop.size() > 10) {
        	ClistPop = ClistPop.subList(0, 10);
        }
        model.addAttribute("boardListPop", boardListPop);
        model.addAttribute("cnumPop", ClistPop);
        

        List<BookDTO> bookList = bookService.bookPagninationByConditionAndFilter(6, 0, "", "title", "book_last_update");

        model.addAttribute("bookList", bookList);

        return "index";
    }
}
