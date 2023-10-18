<%@ tag description="layout" pageEncoding="UTF-8" %>
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

<div class="container bg-white pe-3">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <%-- 여기부터 내용시작 --%>
    <jsp:doBody/>
    <%-- 여기에서 내용 끝 --%>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</html>