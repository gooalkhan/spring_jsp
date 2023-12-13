<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav justify-content-end">
    <c:choose>
        <c:when test="${sname != null}">
        	<c:if test="${sadmin == 'admin' || sadmin == 'subadmin'}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/memberList">회원 목록 보러 가기</a>
            </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/memberDetail?id=${sid}">안녕하세요 ${sname} 님 </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/memberUpdate?id=${sid}">회원 정보 수정</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/shop">포인트 샵</a>
            </li>
            <li class="nav-item">
                <form action="${pageContext.request.contextPath}/memberLogout" method="POST">
                    <a class="nav-link" href="#" onclick="this.parentNode.submit()">로그아웃</a>
                </form>
            </li>
        </c:when>
        <c:otherwise>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/memberLogin">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/memberJoin">회원가입</a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>