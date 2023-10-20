package com.example.spring_jsp.shop.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    //테이블을 만들지 않고 사용할 예정(읽기만 함)
    private String uid;
    private long bookid;
    private String userid;
    private String productid;
    private int usedPoint;
    private LocalDateTime productEndDate;
}