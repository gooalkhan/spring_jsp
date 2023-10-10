<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
<!-- 나중에 멤버가 완성되면, 벨류값 바꿀것 -->
	<div>
		<h1>게시글 등록</h1>
		<form method="Post" action = "boardInsertPost">
			<input type="hidden" name="sid" value="${sid}">
			<div>
				<p>제목 : <input type="text" name ="subject"></p>
			</div>
			<div>
				<p>내용 : <textarea name="content" rows="10"></textarea></p>
			</div>
			<input type="submit" value="글 등록">
		</form>
	</div>
</body>
</html>