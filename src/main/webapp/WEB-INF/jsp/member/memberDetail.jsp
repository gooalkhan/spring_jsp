<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <div class="row">
            <div class="col">
                <div class="mb-3 text-center">
                    <h3>회원 상세 정보</h3>
                </div>
            </div>
        </div>
        <c:if test="${(sid != null && data.id == sid) || sadmin == 'admin' || sadmin == 'subadmin'}">
        <div class="row">
            <div class="col-5 text-end">
                <p>아이디 : </p>
            </div>
            <div class="col-5 text-start">
                    ${data.id}
            </div>
        </div>
        <!-- 비밀번호는 굳이 보여줄 필요가 없으나, 암호화가 잘 되었다라는 것을 손쉽게 볼 수 있도록 남김 -->
        <div class="row">
            <div class="col-5 text-end">
                <p>비밀번호 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${fn:substring(data.pw,0,5)}...</p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>이메일 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${data.email}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>이메일 인증 여부 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${data.hasEmailAuthed}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>이름 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${data.name}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>가입일 : </p>
            </div>
            <div class="col-5 text-start">
                <p><fmt:formatDate pattern="yyyy-MM-dd" value="${data.joinDate}"/></p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>최종 수정일 : </p>
            </div>
            <div class="col-5 text-start">
                <p><fmt:formatDate pattern="yyyy-MM-dd" value="${data.modifyDate}"/></p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>계정 활성화 여부 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${data.enableMember}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>관리자 여부 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${data.admin}</p>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="mb-3 text-center">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/memberUpdate?id=${data.id}">정보 수정</a>

                    <form method="Post" action="${pageContext.request.contextPath}/memberDelete" style="display: inline">
                        <input type="hidden" name="id" value="${data.id}">
                        <a class="btn btn-danger" href="#" onclick="this.parentNode.submit()">회원 탈퇴</a>
                    </form>
                    <a class="btn btn-secondary" href="/">메인으로</a>
                </div>
            </div>
        </div>
    </c:if>
    </div>
    <c:if test="${(sid != null && sadmin == 'admin' && data.admin != 'admin')}">
    <div class="container d-flex justify-content-center">
    	<form method="Post" action="/subadminAppoint">
    		<input type="hidden" name="id" value="${data.id}">
    		<input type="hidden" name="admin" value="${data.admin}">
    		<c:if test="${data.admin != 'subadmin'}">
    		<input type="submit" class="btn btn-success" value="이 유저에게 부관리자 권한 주기">
    		</c:if>
    		<c:if test="${data.admin == 'subadmin'}">
    		<input type="submit" class="btn btn-warning" value="이 유저에게 부관리자 권한 박탈하기">
    		</c:if>
    	</form>
    </div>
    </c:if>
    
</t:layout>