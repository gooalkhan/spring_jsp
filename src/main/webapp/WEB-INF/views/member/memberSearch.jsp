<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원찾기</title>
</head>
<body>
	<h1>회원 찾기</h1>
	<!-- TODO: 콤보박스로 선택하여 어떤 요소로 회원을 찾을 지 선택할 수 있는 기능 구현 -->
	<form method="get" action="/memberDetail">
		<p>원하는 회원의 id를 입력해주세요 : <input type="text" name="id"></p>
		<input type="submit" value="찾기">
	</form>
</body>
</html>