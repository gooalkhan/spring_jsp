<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인홈</title>
</head>
<body>
	<p><a href="/memberLogin">로그인</a></p>
	<form method="Post" action="/memberLogout">
		<input type="hidden" name="sid" value="${sid}">
		<input type="submit" value="로그아웃">
	</form>
	<form method="get" action="/memberDetail">
		<input type="hidden" name="id" value="${sid}">
		<input type="submit" value="내정보">
	</form>
	<p><a href="/memberJoin">회원가입</a>
	<p><a href="/memberSearch">회원찾기</a></p>
	<p><a href="/memberList">모든 회원 정보</a></p>
	<p><a href="/boardList">게시판</a>
	<p>안녕하세요 ${sname} 님</p>
</body>
</html>