package com.example.spring_jsp.member;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String email;
    private Boolean hasEmailAuthed;
    private String name;
    private Timestamp joinDate;
    private Timestamp modifyDate;
}
