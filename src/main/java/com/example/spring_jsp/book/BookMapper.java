package com.example.spring_jsp.book;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    void createTable();

    void dropTable();

    List<BookDTO> bookSelectAll();

    List<BookDTO> bookPagination(int limit, int offset);

    //여러개로 조건을 나누는 이유는 마이바티스에서 컬럼명은 파라미터로 받을 수 없기 때문
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
