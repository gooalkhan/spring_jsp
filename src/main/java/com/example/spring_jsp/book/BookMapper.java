package com.example.spring_jsp.book;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    void createTable();

    void dropTable();

    List<BookDTO> bookSelectAll();

    List<BookDTO> bookPagination(@Param("limit") int limit, @Param("offset") int offset);

    //여러개로 조건을 나누는 이유는 마이바티스에서 컬럼명은 파라미터로 받을 수 없기 때문
    List<BookDTO> bookPaginationByTitle(@Param("limit") int limit, @Param("offset") int offset, @Param("searchword") String searchword);
    List<BookDTO> bookPaginationByAuthor(@Param("limit") int limit, @Param("offset") int offset, @Param("searchword") String searchword);
    List<BookDTO> bookPaginationByPublisher(@Param("limit") int limit, @Param("offset") int offset, @Param("searchword") String searchword);

    int bookInsert(BookDTO bookDTO);

    int bookCount();
    int bookCountByTitle(@Param("searchword") String searchword);
    int bookCountByAuthor(@Param("searchword") String searchword);
    int bookCountByPublisher(@Param("searchword") String searchword);

    BookDTO bookSelect(@Param("bookid") long bookid);

    int bookUpdate(BookDTO bookDTO);

    List<BookDTO> bookPagninationByConditionAndFilter(@Param("limit") int limit, @Param("offset") int offset, @Param("searchword") String searchword, @Param("condition") String condition, @Param("filter") String filter);
}
