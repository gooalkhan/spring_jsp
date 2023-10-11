<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.subject}</title>
</head>
<body>
	<h1>글 상세 정보</h1>
	<p>제목 : ${data.subject}</p>
	<p>내용 : ${data.content}</p>
	<p>게시일 : ${data.postDate}</p>
	<p>글쓴이 : ${data.membertbl_id}</p>
	<p>댓글 목록</p>
	<table>
	    <thead>
	        <tr>
	            <th>내용</th>
	            <th>글쓴이</th>
	            <th>올린날짜</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="show" items="${show}">
	        <tr>   
	            <td>${show.content}</td>
	            <td>${show.name}</td>
	            <td>${show.postDate}</td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<p>댓글</p>
	<form method="Post" action="/commentCreate">
		<input type="hidden" name="membertbl_id" value="${sid}">
		<input type="hidden" name="boardtbl_idx" value="${data.idx}">
		<textarea cols="50" rows="10" name="content"></textarea>
		<input type="submit" value="댓글쓰기">	
	</form>
	
	<p><a href="/boardUpdate?idx=${data.idx}">수정</a></p>
	
	<form method="Post" action="/boardDelete">
		<input type="hidden" name="id" value="${data.idx}">
		<input type="submit" value="삭제">
	</form>
	<p><a href="/boardList">게시글 목록으로</a>
	<p><a href="/">메인으로</a></p>
</body>
</html>