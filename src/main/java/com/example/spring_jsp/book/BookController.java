package com.example.spring_jsp.book;

import com.example.spring_jsp.book.keyword.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final KeywordService keywordService;

    private final int BOOK_PAGE_SIZE = 12;

    @GetMapping("")
    public String bookSelect(@RequestParam(name = "page", defaultValue = "1") int page,
                             @RequestParam(name = "id", defaultValue = "0") long id,
                             @RequestParam(name = "condition", defaultValue = "") String condition,
                             @RequestParam(name = "searchword", defaultValue = "") String searchword,
                             Model model) {
        int count; // 조건에 맞는 책의 개수 - 페이징 처리를 위해 사용
        if (id != 0) model.addAttribute("id", id);

        List<BookDTO> data; // 책 정보
        if (!condition.isEmpty() && !searchword.isEmpty()) {
            data = switch (condition) {
                case "title" -> {
                    count = bookService.bookCountByTitle(searchword);
                    yield bookService.bookPaginationByTitle(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE, searchword);
                }
                case "author" -> {
                    count = bookService.bookCountByAuthor(searchword);
                    yield bookService.bookPaginationByAuthor(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE, searchword);
                }
                case "publisher" -> {
                    count = bookService.bookCountByPublisher(searchword);
                    yield bookService.bookPaginationByPublisher(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE, searchword);
                }
                default -> {
                    count = bookService.bookCount();
                    yield bookService.bookPagination(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE);
                }
            };
        } else {
            count = bookService.bookCount();
            data = bookService.bookPagination(BOOK_PAGE_SIZE, (page - 1) * BOOK_PAGE_SIZE);
        }

        model.addAttribute("data", data); // 책 정보 추가
        model.addAttribute("page", page); // 현재 페이지 추가
        // 총 페이지 수 추가 - 총 개수를 페이지 사이즈로 나눈 값에 나머지가 있으면 페이지 수를 1 더해줌
        model.addAttribute("pageCount", count % BOOK_PAGE_SIZE > 0 ? count / BOOK_PAGE_SIZE + 1 : count / BOOK_PAGE_SIZE);
        return "book/books";
    }

    @GetMapping("/bookDetail")
    public String bookDetail(@RequestParam(name = "id") long id, Model model) {
        model.addAttribute("id", id);
        BookDTO bookDTO = bookService.bookSelect(id);
        model.addAttribute("title", bookDTO.getTitle());
        return "book/bookDetail";
    }

    @GetMapping("/bookcard")
    public String bookcard(@RequestParam("bookid") long bookid, @RequestParam(value = "detail", defaultValue = "false") boolean isDetail, Model model) {

        model.addAttribute("detail", isDetail);

        model.addAttribute("keywords", keywordService.keywordSelect(bookid));
        model.addAttribute("id", bookid);
        model.addAttribute("selectedBook", bookService.bookSelect(bookid));

        return "book/bookcard";
    }
}
