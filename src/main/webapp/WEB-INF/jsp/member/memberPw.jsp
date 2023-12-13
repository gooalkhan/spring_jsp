<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<div class="container py-3">
	    <div class="row justify-content-center">
            <div class="m-3 text-center">
            	<c:if test="${data.pw != null}">
                <h3>회원님의 비밀번호를 재설정 하겠습니다.</h3>
                <form name="memberUpdateForm" method="Post" action="${pageContext.request.contextPath}/resetPw" onsubmit="return validateMemberForm('update')">
                	<input type="hidden" name="id" value="${data.id}">
                	<div class="d-grid gap-3">
                        <div class="mb-3">
                            <label for="pw">비밀번호</label>
                            <input type="password" id="pw" class="form-control" name="pw" placeholder="비밀번호" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$" required title="비밀번호는 8~20자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.">
                        </div>
                        <div class="mb-3">
                            <label for="pw2">비밀번호(재입력)</label>
                            <input type="password" id="pw2" class="form-control" name="pw2" placeholder="비밀번호 확인" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$" required title="비밀번호는 8~20자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.">
                        </div>
                    </div>
                    <div class="container d-flex justify-content-center">
                        <input type="submit" class="btn btn-primary" value="비밀번호 재설정하기">
                    </div>
                </form>
                </c:if>
                <c:if test="${data.pw == null}">
                <h3>일치하는 비밀번호가 없습니다. 다시 시도해주세요.</h3>
                </c:if>
            </div>
        </div>
        <c:if test="${data.pw != null}">
	        <div class="d-grid gap-3">
		        <div class="container d-flex justify-content-center" style="width: 200px;">
		        	<a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/memberLogin">로그인하러 가기</a>
		       	</div>
	       	</div>
       	</c:if>
       	<c:if test="${data.pw == null}">
	       	<div class="d-grid gap-3">
				<div class="container d-flex justify-content-center" style="width: 200px;">
		        	<a class="btn btn-outline-success btn-lg" href="${pageContext.request.contextPath}/whereIsMyPw">비밀번호 찾기 재시도</a>
		       	</div>
	       	</div>
       	</c:if>
	</div>
</t:layout>