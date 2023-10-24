package com.example.spring_jsp.analysis.python;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PythonMapper {
    int insertPython(PythonDTO pythonDTO);

    void createTable();

    void dropTable();

    PythonDTO selectPython(@Param("bookId") long bookId, @Param("productId") String productId);
}
