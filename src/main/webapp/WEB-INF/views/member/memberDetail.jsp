<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="row">
            <div class="col-5 text-end">
                <p>아이디 : </p>
            </div>
            <div class="col-5 text-start">
                    ${data.id}
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-end">
                <p>비밀번호 : </p>
            </div>
            <div class="col-5 text-start">
                <p>${data.pw}</p>
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
        <!-- TODO : 나중에 0이면 뭐뜨고 1이면 뭐뜨고 이렇게 수정 -->
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
            <div class="col">
                <div class="mb-3 text-center">
                    <!-- TODO: 나중에 세션 아이디가 같을 때만 정보 수정이 가능하도록, 그리고 이 버튼이 뜨도록 수정 -->
                    <a href="/memberUpdate?id=${data.id}">정보 수정</a>

                    <!-- TODO: 나중에 세션 아이디가 같을 때만 회원 삭제가 가능하도록 수정, 그리고 이 버튼이 뜨도록 수정 -->
                    <form method="Post" action="/memberDelete" style="display: inline">
                        <input type="hidden" name="id" value="${data.id}">
                        <a href="#" onclick="this.parentNode.submit()">삭제</a>
                    </form>
                    <a href="/">메인으로</a>
                </div>
            </div>
        </div>
    </div>
</t:layout>