package com.example.spring_jsp.member;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String email;
    private Boolean hasEmailAuthed;
    private String name;
    private Timestamp joindate;
    private Timestamp modifydate;
}
