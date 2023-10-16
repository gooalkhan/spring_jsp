<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav justify-content-end">
    <c:choose>
        <c:when test="${sname != null}">
            <li class="nav-item">
                <a class="nav-link" href="/memberDetail?id=${sid}">안녕하세요 ${sname} 님 </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/memberUpdate?id=${sid}">회원 정보 수정</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/shop">포인트 샵</a>
            </li>
            <li class="nav-item">
                <form action="/memberLogout" method="POST">
                    <a class="nav-link" href="#" onclick="this.parentNode.submit()">로그아웃</a>
                </form>
            </li>
        </c:when>
        <c:otherwise>
            <li class="nav-item">
                <a class="nav-link" href="/memberLogin">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/memberJoin">회원가입</a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>