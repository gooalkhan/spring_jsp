<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<h1>회원 목록</h1>
 	<table>
	    <thead>
	        <tr>
	            <th>아이디</th>
	            <th>비밀번호</th>
	            <th>이메일</th>
	            <th>이름</th>
	            <th>가입일</th>
	            <th>최종수정일</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="data" items="${data}">
	        <tr>   
	            <td><a href="/memberDetail?id=${data.id}">${data.id}</a></td>
	            <td>${data.pw}</td>
	            <td>${data.email}</td>
	            <td>${data.name}</td>
	            <td>${data.joinDate}</td>
	            <td>${data.modifyDate}</td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<p><a href="/">메인으로</a></p>
</body>
</html>