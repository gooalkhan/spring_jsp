<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${sadmin == 'admin' || sadmin == 'subadmin'}">
    <t:layout>
        <div class="container py-3">

            <div class="row">
                <div class="col text-center mb-3"><h3>회원 목록</h3></div>
            </div>
            <table class="table table-borderless">
                <thead>
                <tr>
                    <th>아이디</th>
                    <th>비밀번호</th>
                    <th>이메일</th>
                    <th>이메일 인증 여부</th>
                    <th>이름</th>
                    <th>가입일</th>
                    <th>최종수정일</th>
                    <th>계정 활성화/비활성화 여부</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="data" items="${data}">
                    <tr>
                        <td><a href="/memberDetail?id=${data.id}">${data.id}</a></td>
                        <td>${data.pw}</td>
                        <td>${data.email}</td>
                        <td>${data.hasEmailAuthed}</td>
                        <td>${data.name}</td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${data.joinDate}"/></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${data.modifyDate}"/></td>
                        <td>${data.enableMember}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>
            <div class="container d-flex justify-content-center">
                <form method="get" action="/memberDetail">
                    <div class="row">
                        <div class="col-auto"><input class="form-control" type="text" name="id" id="id" placeholder="아이디로 검색"></div>
                        <div class="col-auto"><input class="btn btn-primary" type="submit" value="찾기"></div>
                    </div>
                </form>
            </div>
        </div>
    </t:layout>

</c:if>