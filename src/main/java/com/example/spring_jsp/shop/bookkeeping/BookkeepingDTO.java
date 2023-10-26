package com.example.spring_jsp.shop.bookkeeping;

import lombok.Data;

import java.util.Date;

@Data
public class BookkeepingDTO {

    private String UUID;
    private String referenceUUID; // 포인트 증정시에 원래구매 UUID를 참조하도록 설정
    private String userid; //membertbl에서 외래 키로 가져옴
    private int addedPoint;  //구입한 포인트
    private int usedPoint;  //사용한 포인트
    private String purchaseMethodUID; //구매방법
    private String campaignUID;  //구매했을때의 행사, html아이디로 쓰이므로 숫자로 시작해서는 안된다.
    private String unlockedUID;  //구매한 분석정보
    private Date addDate;  //구매일자
}
