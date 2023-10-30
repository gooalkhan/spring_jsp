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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    //TODO: 첫 화면에서 보여줄 게시글, 새로 업데이트된 웹소설 보여주기
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
        model.addAttribute("boardList", boardList);

        List<BookDTO> bookList = bookService.bookPagninationByConditionAndFilter(6, 0, "", "title", "book_last_update");

        model.addAttribute("bookList", bookList);

        return "index";
    }
}
