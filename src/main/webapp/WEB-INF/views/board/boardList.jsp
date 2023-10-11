<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css"/>
    <title>브랜드명</title>
</head>

<body>

<div class="container bg-white">
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%-- 여기부터 내용시작 --%>
    <div class="container pb-3">
    <table class="table table-striped">
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
                <td>${data.membertbl_id}</td>
                <td>${data.postDate}</td>
                <td>${data.views}</td>
                <td>${data.likes}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="container d-flex justify-content-around">
        <a href="/boardInsert">글쓰기</a>
        <a href="/">메인으로</a>
        <form action="/boardSearch">
            <input type="text" name="subject" placeholder="제목으로 검색">
            <button type="submit">검색</button>
        </form>
    </div>
    </div>
</div>

    <%-- 여기에서 내용 끝 --%>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>