package com.example.spring_jsp.shop.product;

import lombok.Data;

import java.time.LocalDateTime;

/*
 * 구매한 분석정보 DTO
 * 빌더 클래스를 통해 생성된다. 사용자가 조회한 분석정보 페이지에서 동적으로 생성한다.
 * 이론적으로 세션수*분석수*책수만큼 생성된다.
 */
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