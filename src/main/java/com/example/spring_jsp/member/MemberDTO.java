package com.example.spring_jsp.member;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberDTO {
	//membertbl에 있는 컬럼들의 값을 담기 위한 변수
    private String id;
    private String pw;
    private String email;
    private Boolean hasEmailAuthed;
    private String name;
    private Timestamp joinDate;
    private Timestamp modifyDate;
    private boolean enableMember;
    private String admin;
    
    //그 외에 값을 담기 위해 만든 변수
}
