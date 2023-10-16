package com.example.spring_jsp.book;

import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {
    private long bookid;
    private String title;
    private String author;
    private String publisher;
    private Date publish_date;
    private Date reg_date;
    private String category;
    private Integer review_count;
    private Integer preference_count;
    private Integer series_count;
    private String description;
    private Boolean is_complete;
    private Boolean is_gidamu;
    private Boolean is_adult_only;
    private Date last_update;
}
