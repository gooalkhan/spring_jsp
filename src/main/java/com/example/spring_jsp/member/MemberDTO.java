package com.example.spring_jsp.member;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {
    private int idx;
    private String id;
    private String pw;
    private String email;
    private int hasEmailAuthed;
    private String name;
    private Date joinDate;
    private Date modifyDate;
}
