<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <div class="container pb-3">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>게시일</th>
                <th>조회수</th>
                <th>좋아요</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="data" items="${data}" varStatus="status">
                <tr>
                    <td>${data.idx}</td>
                    <td style="font-size: 10px;"><a href="/boardDetail?idx=${data.idx}" style="font-size: medium; text-decoration: none;">${data.subject}</a>&nbsp;&nbsp;&nbsp;${cnum[status.index].count}</td>
                    <td>${data.name}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${data.postDate}"/></td>
                    <td>${data.views}</td>
                    <td>${data.likes}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="container d-flex justify-content-around">
        	<div class="col-auto">
            	<a href="/boardInsert" class="btn btn-primary">글쓰기</a>
            </div>
            <div class="col-auto">
            <a href="/" class="btn btn-primary">메인으로</a>
            </div>
            <form action="/boardSearch">
                <div class="row">
                    <div class="col-auto">
                        <input type="text" class="form-control" name="subject" placeholder="제목으로 검색">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</t:layout>