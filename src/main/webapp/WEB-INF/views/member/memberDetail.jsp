<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<h1>회원 상세 정보</h1>
	<p>아이디 : ${data.id}</p>
	<p>비밀번호 : ${data.pw}</p>
	<p>이메일 : ${data.name}</p>
	 <!-- TODO : 나중에 0이면 뭐뜨고 1이면 뭐뜨고 이렇게 수정 -->
	<p>이메일 인증 여부 : ${data.hasEmailAuthed}</p>
	<p>이름 : ${data.email}</p>
	<p>가입일 : ${data.joinDate}</p>
	<p>최종 수정일 : ${data.modifyDate}</p>
	
	<!-- TODO: 나중에 세션 아이디가 같을 때만 정보 수정이 가능하도록, 그리고 이 버튼이 뜨도록 수정 -->
	<p><a href="/memberUpdate?id=${data.id}">정보 수정</a></p>
	
	<!-- TODO: 나중에 세션 아이디가 같을 때만 회원 삭제가 가능하도록 수정, 그리고 이 버튼이 뜨도록 수정 -->
	<form method="Post" action="/memberDelete">
		<input type="hidden" name="id" value="${id}">
		<input type="submit" value="삭제">
	</form>
	<p><a href="/">메인으로</a></p>
</body>
</html>