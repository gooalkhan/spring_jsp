package com.example.spring_jsp.book;

import com.example.spring_jsp.book.keyword.KeywordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookServiceImpl;
    private final KeywordServiceImpl keywordServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int BOOK_PAGE_SIZE = 12;

    @GetMapping("")
    public String bookSelect(@RequestParam(name="page", defaultValue = "1") int page,
                             @RequestParam(name="id", defaultValue = "0") long id,
                             @RequestParam(name="condition", defaultValue = "") String condition,
                             @RequestParam(name="searchword", defaultValue = "") String searchword,
                             Model model) {
        int count;

        List<BookDTO> data;
        if (!condition.isEmpty() && !searchword.isEmpty()) {
            switch (condition) {
                case "title":
                    count = bookServiceImpl.bookCountByTitle(searchword);
                    data = bookServiceImpl.bookPaginationByTitle(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE, searchword);
                    break;
                case "author":
                    count = bookServiceImpl.bookCountByAuthor(searchword);
                    data = bookServiceImpl.bookPaginationByAuthor(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE, searchword);
                    break;
                case "publisher":
                    count = bookServiceImpl.bookCountByPublisher(searchword);
                    data = bookServiceImpl.bookPaginationByPublisher(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE, searchword);
                    break;
                default:
                    count = bookServiceImpl.bookCount();
                    data = bookServiceImpl.bookPagination(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE);
                    break;
            }
        } else {
            count = bookServiceImpl.bookCount();
            data = bookServiceImpl.bookPagination(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE);
        }

        if (id != 0) {
            model.addAttribute("keywords", keywordServiceImpl.keywordSelect(id));
            model.addAttribute("id", id);
            model.addAttribute("selectedBook", bookServiceImpl.bookSelect(id));
        }
        model.addAttribute("data", data);
        model.addAttribute("page", page);
        model.addAttribute("pageCount", count%BOOK_PAGE_SIZE > 0 ? count/BOOK_PAGE_SIZE + 1 : count/BOOK_PAGE_SIZE);
        return "/book/books";
    }

}
