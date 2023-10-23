package com.example.spring_jsp.analysis.python;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PythonDTO {
    private String jobUid;
    private long bookId;
    private String productId;
    private String stringTemplate;
    private LocalDateTime created;
}
