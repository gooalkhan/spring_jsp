package com.example.spring_jsp.book;

import java.util.List;

public interface BookService {

    public void createTable();

    public void dropTable();

    public List<BookDTO> bookSelectAll();

    public List<BookDTO> bookPagination(int limit, int offset);

    public List<BookDTO> bookPaginationByTitle(int limit, int offset, String searchword);
    public List<BookDTO> bookPaginationByAuthor(int limit, int offset, String searchword);
    public List<BookDTO> bookPaginationByPublisher(int limit, int offset, String searchword);

    public int bookInsert(BookDTO bookDTO);

    public int bookCount();

    public int bookCountByTitle(String searchword);

    public int bookCountByAuthor(String searchword);
    public int bookCountByPublisher(String searchword);
    public BookDTO bookSelect(long bookid);
}
