package com.example.spring_jsp.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    public void createTable() {
        bookMapper.createTable();
    }

    public void dropTable() {
        bookMapper.dropTable();
    }

    public List<BookDTO> bookSelectAll() {
        return bookMapper.bookSelectAll();
    }

    public List<BookDTO> bookPagination(int limit, int offset) {
        return bookMapper.bookPagination(limit, offset);
    }

    public List<BookDTO> bookPaginationByTitle(int limit, int offset, String searchword) {
        return bookMapper.bookPaginationByTitle(limit, offset, searchword);
    }
    public List<BookDTO> bookPaginationByAuthor(int limit, int offset, String searchword) {
        return bookMapper.bookPaginationByAuthor(limit, offset, searchword);
    }
    public List<BookDTO> bookPaginationByPublisher(int limit, int offset, String searchword) {
        return bookMapper.bookPaginationByPublisher(limit, offset, searchword);
    }

    public int bookInsert(BookDTO bookDTO) {
        return bookMapper.bookInsert(bookDTO);
    }

    public int bookCount() {
    	return bookMapper.bookCount();
    }

    public int bookCountByTitle(String searchword) {
    	return bookMapper.bookCountByTitle(searchword);
    }

    public int bookCountByAuthor(String searchword) {
    	return bookMapper.bookCountByAuthor(searchword);
    }

    public int bookCountByPublisher(String searchword) {
    	return bookMapper.bookCountByPublisher(searchword);
    }

    public BookDTO bookSelect(long bookid) {
    	return bookMapper.bookSelect(bookid);
    }
}
