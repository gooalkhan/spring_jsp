<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sid == 'hong'}">
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
	            <th>이메일 인증 여부</th>
	            <th>이름</th>
	            <th>가입일</th>
	            <th>최종수정일</th>
	            <th>계정 활성화/비활성화 여부</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="data" items="${data}">
	        <tr>   
	            <td><a href="/memberDetail?id=${data.id}">${data.id}</a></td>
	            <td>${data.pw}</td>
	            <td>${data.email}</td>
	            <td>${data.hasEmailAuthed}</td>
	            <td>${data.name}</td>
	            <td>${data.joinDate}</td>
	            <td>${data.modifyDate}</td>
	            <td>${data.enableMember}</td>
	        </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<form method="get" action="/memberDetail">
		<p>찾고자 하는 회원의 id를 입력해주세요 :
		<input type="text" name="id">
		<input type="submit" value="찾기">
		</p>
	</form>
	<p><a href="/">메인으로</a></p>
</body>
</html>
</c:if>