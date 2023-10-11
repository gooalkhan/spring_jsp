<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form method="Post">
		<p>아이디 : <input type="text" value="${data.id}" readonly></p>
		<p>비밀번호 : <input type="password" name="pw" value="${data.pw}"></p>
		<p>이름 : <input type="text" name="name" value="${data.name}"></p>
		<p>이메일 : <input type="email" name="email" value="${data.email}"></p>
		<input type="submit" value="변경사항저장">
	</form>
</body>
</html>