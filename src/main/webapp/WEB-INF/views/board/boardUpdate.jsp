<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<form method="Post">
		<p>게시글 번호 : <input type="text" value="${data.idx}" readonly></p>
		<p>제목 : <input type="text" name="subject" value="${data.subject}"></p>
		<p>내용 : <textarea cols="50" rows="10" name="content">${data.content}</textarea></p>
		<input type="submit" value="변경사항저장">
	</form>
</body>
</html>