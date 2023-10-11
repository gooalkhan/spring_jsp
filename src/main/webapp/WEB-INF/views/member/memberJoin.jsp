<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form method="Post">
		<p>아이디 : <input type="text" name="id" placeholder="아이디"></p>
		<p>비밀번호 : <input type="password" name="pw" placeholder="비밀번호"></p>
		<p>이메일 : <input type="email" name="email" placeholder="이메일"></p>
		<p>이름 : <input type="text" name="name" placeholder="이름"></p>
		<input type="submit" value="가입하기">
	</form>
</body>
</html>