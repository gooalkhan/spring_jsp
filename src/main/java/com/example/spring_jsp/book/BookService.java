package com.example.spring_jsp.book;

import java.util.List;

public interface BookService {

    void createTable();

    void dropTable();

    List<BookDTO> bookSelectAll();

    List<BookDTO> bookPagination(int limit, int offset);

    List<BookDTO> bookPaginationByTitle(int limit, int offset, String searchword);
    List<BookDTO> bookPaginationByAuthor(int limit, int offset, String searchword);
    List<BookDTO> bookPaginationByPublisher(int limit, int offset, String searchword);

    int bookInsert(BookDTO bookDTO);

    int bookCount();

    int bookCountByTitle(String searchword);

    int bookCountByAuthor(String searchword);
    int bookCountByPublisher(String searchword);
    BookDTO bookSelect(long bookid);
}
