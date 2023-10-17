package com.example.spring_jsp.shop.campaign;

import lombok.Data;

import java.util.Date;

@Data
public class CampaignDTO {
//테이블을 만들지 않고 사용할 예정(읽기만 함)
    private String uid;
    private String campaignName;
    private String campaignDescription;
    private int point;
    private int additionalPoint;
    private Date campaignEndDate;
}
