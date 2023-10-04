create table MEMBERTBL
(
    IDX              INTEGER auto_increment,
    ID               CHARACTER VARYING(20)            not null
        constraint MEMBERTBL_PK
        unique,
    PW               CHARACTER VARYING(100)           not null,
    EMAIL            CHARACTER VARYING(100),
    "hasEmailAuthed" BOOLEAN   default 0              not null,
    NAME             CHARACTER VARYING(30)            not null,
    JOINDATE         TIMESTAMP                        not null,
    MODIFYDATE       TIMESTAMP default LOCALTIMESTAMP not null,
    constraint MEMBERTBL_PK2
        primary key (IDX)
);