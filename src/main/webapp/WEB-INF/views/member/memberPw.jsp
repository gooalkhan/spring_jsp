<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<div class="container py-3">
	    <div class="row justify-content-center">
            <div class="m-3 text-center">
            	<c:if test="${data.pw != null}">
                <h3>회원님의 비밀번호는 ${data.pw} 입니다</h3>
                </c:if>
                <c:if test="${data.pw == null}">
                <h3>일치하는 비밀번호가 없습니다. 다시 시도해주세요.</h3>
                </c:if>
            </div>
        </div>
        <c:if test="${data.pw != null}">
	        <div class="d-grid gap-3">
		        <div class="container d-flex justify-content-center" style="width: 200px;">
		        	<a class="btn btn-outline-primary" href="/memberLogin">로그인하러 가기</a>
		       	</div>
	       	</div>
       	</c:if>
       	<c:if test="${data.pw == null}">
	       	<div class="d-grid gap-3">
				<div class="container d-flex justify-content-center" style="width: 200px;">
		        	<a class="btn btn-outline-success btn-lg" href="/whereIsMyPw">비밀번호 찾기 재시도</a>
		       	</div>
	       	</div>
       	</c:if>
	</div>
</t:layout>