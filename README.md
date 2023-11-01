# 북투게더(BookTOGETHER)
게시판 및 파이썬을 활용한 실시간 웹소설 분석 기능을 갖춘 웹 애플리케이션

## 제작 기간
2023년 10월 4일 ~ 2023년 11월 1일

## 기능
* 게시판(CRUD)
* 이상형 월드컵 작성 및 삭제
* 회원 가입, 관리 및 수정
* 포인트샵을 통한 포인트 구매
* 실시간 푸시 메시지
* 웹소설 실시간 분석

## 담당 작업
* FormDog: 회원, 게시판, 이상형 월드컵 프론트엔드와 백엔드
* gooalkhan: 푸시메시지, 포인트샵, 파이썬 실시간 분석 백엔드, 프로젝트 전반의 프론트엔드와 UI/UX 디자인

## Requirements
* 스프링 부트 3 이상을 사용하므로 자바 17버전 이상 설치 필요
* 파이썬 연동위해 아나콘다 설치 필요
* sts4 사용 시, Eclipse Enterprise Java and Web Developer Tools 설치 필요
* MySQL 설치 필요(배포 환경)
* 톰캣 10버전
* 실행시 VM arguments에 -Dspring.profiles.active=dev 또는 -Dspring.profiles.active=prod 추가 필요(개발, 배포환경)
* 실행시 VM arguments에 -Dfile.encoding=UTF-8 추가 필요(톰캣 한글깨짐 방지)
* 배포방식: war-exploded

## Technical stacks(상세내역은 BOM 참조)

### Backend
* 웹 서버: Tomcat 10
* 웹 어플리케이션 서버: Spring boot 3
* 데이터베이스: H2(개발환경), MySQL(배포환경)
* 데이터베이스 매핑: Mybatis
* 파이썬 실행: Apache commons exec
* 파이썬 분석작업: anaconda, pandas
* 푸시메시지: websocket

### Frontend
* ui: jsp, 부트스트랩 5
* 푸시메시지: SockJS
* 그래프: chartjs
