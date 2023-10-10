<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<div>
        <h1>게시판</h1>
        <table>
            <thead>
                <tr>
                    <th>글 번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>게시일</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="data" items="${data}">
                    <tr>
                        <td>${data.idx}</td>
                        <td><a href="/boardDetail?idx=${data.idx}">${data.subject}</a></td>
                        <td>${data.membertbl_idx}</td>
                        <td>${data.postdate}</td>
                        <td>${data.views}</td>
                        <td>${data.likes}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="/boardSearch">
            <div>
                <input type="text" name="subject" placeholder="제목으로 검색">
                <div>
                    <button type="submit">검색</button>
                </div>
            </div>
        </form>
        <p><a href="/boardInsert">글쓰기</a></p>
        <p><a href="/">메인으로</a></p>
    </div>
</body>
</html>