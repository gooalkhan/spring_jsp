package com.example.spring_jsp.shop.bookkeeping;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookkeepingMapper {

    void createTable();

    void dropTable();

    int bookkeepingInsert(BookkeepingDTO bookkeepingDTO);

    List<BookkeepingDTO> bookkeepingSelect(String userid);

}
