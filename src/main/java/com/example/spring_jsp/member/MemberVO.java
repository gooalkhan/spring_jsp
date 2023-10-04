package com.example.spring_jsp.member;

import lombok.Data;

import java.util.Date;

@Data
public class MemberVO {
    private int idx;
    private String id;
    private String pw;
    private String email;
    private Boolean hasEmailAuthed;
    private String name;
    private Date joindate;
    private Date modifydate;
}
