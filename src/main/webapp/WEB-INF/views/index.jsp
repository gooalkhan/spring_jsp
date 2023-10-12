<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <div class="container d-flex justify-content-center">
      <p class="lorem">"Lorem ipsum dolor sit amet, consectetur adipiscing elit,<br>
          sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.<br>
          Ut enim ad minim veniam,<br>
          quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.<br>
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.<br>
          Excepteur sint occaecat cupidatat non proident,<br>
          sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
    </div>

</div>

<%-- 여기에서 내용 끝 --%>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</html>