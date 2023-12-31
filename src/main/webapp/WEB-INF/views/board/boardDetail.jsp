<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <div class="container p-3">
        <div class="container">
            <div class="row mb-3">
                <h4>${data.subject}</h4>
            </div>
            <div class="row mb-3 justify-content-start">
                <div class="col-2">
                    <span class="desc">글쓴이 : ${data.name}</span>
                </div>
                <div class="col-2">
                    <span class="desc">게시일 : <fmt:formatDate pattern="yyyy-MM-dd" value="${data.postDate}"/></span>
                </div>
                <div class="col-2">
                    <span class="desc">조회수 : ${data.views}</span>
                </div>
                <div class="col-2">
                    <span class="desc">좋아요 : ${data.likes}</span>
                </div>
            </div>
            <div class="row mb-3">
            	<c:forEach var="image" items="${image}">
                    <img src="/boardimages/${image.imageName}" alt="이미지" style="width: 20%; height: auto;">
                </c:forEach>
                <article>
                    <p class="border p-3 mt-3">${data.content}</p>
                </article>
            </div>
            <div class="container d-flex justify-content-center">
            	<div class="row">
                <c:if test="${data.membertbl_id == sid || sadmin == 'admin' || sadmin == 'subadmin'}">
                    
                        <div class="col">
                            <button type="button" class="btn btn-primary"
                                    onclick="location.href='/boardUpdate?idx=${data.idx}'">수정
                            </button>
                        </div>
                        <div class="col">
                            <form method="Post" action="/boardDelete">
                                <input type="hidden" name="idx" value="${data.idx}">
                                <input type="submit" class="btn btn-danger" value="삭제">
                            </form>
                        </div>
                </c:if>
                <c:if test="${sid != null}">
                        <div class="col">
                            <form method="Post" action="/boardLike">
                            	<input type="hidden" name="boardtbl_idx" value="${data.idx}">
                				<input type="hidden" name="membertbl_id" value="${sid}">
                				<c:if test="${like.id != sid}">
                                <input type="submit" class="btn btn-success" value="좋아요">
                                </c:if>
                               	<c:if test="${like.id == sid}">
                                <input type="submit" class="btn btn-warning" value="좋아요 취소">
                                </c:if>
                            </form>
                        </div>
                </c:if>
                </div>
            </div>
        </div>
        <hr>
        <div class="container">
            <h5>댓글</h5>
            <table class="table table-borderless">
                <thead>
                <tr>
                    <th scope="col" class="text-start">글쓴이</th>
                    <th scope="col" class="text-center">내용</th>
                    <th scope="col" class="text-center">올린날짜</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="show" items="${show}">
                    <tr>
                        <td class="text-start">${show.name}</td>
                        <td class="text-center">${show.content}</td>
                        <td class="text-center"><fmt:formatDate pattern="yyyy-MM-dd" value="${show.postDate}"/></td>
                        <td class="text-end">
                            <c:if test="${show.name == sname || sadmin == 'admin' || sadmin == 'subadmin'}">
                                <form method="Post" action="commentDelete">
                                    <input type="hidden" name="idx" value="${show.idx}">
                                    <input type="hidden" name="bidx" value="${data.idx}">
                                    <a href="#" onclick="this.parentNode.submit()">삭제</a>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <hr>
        <div class="container">

            <form method="Post" name="comment_form" onsubmit="return comment_form_check()" action="/commentInsert">
                <input type="hidden" name="membertbl_id" value="${sid}">
                <input type="hidden" name="boardtbl_idx" value="${data.idx}">
                <div class="mb-3">
                    <label for="comment_ta" class="form-label">댓글 쓰기</label>
                    <textarea class="form-control" id="comment_ta" name="content" required></textarea>
                </div>
                <div class="mb-3">
                    <input type="submit" class="btn btn-primary" value="댓글 쓰기">
                </div>
            </form>
        </div>
        <div class="container d-flex justify-content-evenly">
            <span><a href="/boardList">게시글 목록으로</a></span>
            <span><a href="/">메인으로</a></span>
        </div>
    </div>
</t:layout>