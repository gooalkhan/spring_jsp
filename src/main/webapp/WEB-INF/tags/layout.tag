<%@ tag description="layout" pageEncoding="UTF-8" %>
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

<div class="container bg-white pe-3">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <%-- 여기부터 내용시작 --%>
    <jsp:doBody/>
    <%-- 여기에서 내용 끝 --%>

    <!-- 토스트 컨테이너 -->
    <div class="toast-container position-fixed bottom-0 end-0 p-3" id="toast-container">
        <!-- 토스트 메시지 -->
        <div class="toast" id="myToast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="me-auto">알림</strong>
                <small class="text-muted" id="toast-title">
<%-- 웹소켓 수신 시각 --%>
                </small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body" id="toast-body">
<%-- 웹소켓 수신 내용 --%>
            </div>
        </div>
    </div>

</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
<script src="${pageContext.request.contextPath}/resources/js/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</html>