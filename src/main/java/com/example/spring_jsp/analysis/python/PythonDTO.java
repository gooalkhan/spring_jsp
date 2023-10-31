package com.example.spring_jsp.analysis.python;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 파이썬 작업완료시 데이터베이스로 insert되는 DTO(stringTemplate 부분은 html문서임)
 */
@Data
public class PythonDTO {
    private String jobUid;
    private long bookId;
    private String productId;
    private String stringTemplate;
    private LocalDateTime created;
}
