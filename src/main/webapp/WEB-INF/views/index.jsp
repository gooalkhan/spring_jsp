<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <div class="container py-3">
        <div class="row">
            <div class="card col-12 mb-3" id="newbook">
                <div class="card-body">
                    <h5 class="mb-3"><b>최신 업데이트 웹소설</b></h5>
                    <div class="container d-flex justify-content-center">
                        <div class="row">
                            <c:forEach var="book" items="${bookList}" varStatus="status">
                                <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2 mb-3">
                                    <div class="card" style="min-width: 140px;">
                                        <a href="books?page=${page}&id=${book.bookid}&condition=${param.condition}&searchword=${param.searchword}">
                                            <svg class="card-img-top book-pic" width="100%" height="200">
                                                <text x="30" y="100">
                                                        ${book.bookid}
                                                </text>
                                                <text x="30" y="120">
                                                    표지
                                                </text>
                                                <rect width="100%" height="100%" fill="purple"
                                                      fill-opacity="0.3"/>
                                            </svg>
                                        </a>
                                        <div class="card-body book-card">
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="card-title">${book.title}</h6></div>
                                                <div class="col-12"><p class="card-text"><small>${book.author}</small>
                                                </p></div>
                                                <div class="col-12"><p class="card-text">
                                                    <small>${book.publisher}</small></p></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card col-12 mb-3" id="newboard">
                <div class="card-body">
                    <h5><b>신규 게시글</b></h5>
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
                        <c:forEach var="article" items="${boardList}" varStatus="status">
                            <tr>
                                <td>${article.idx}</td>
                                <td style="font-size: 10px;"><a href="/boardDetail?idx=${article.idx}" style="font-size: medium; text-decoration: none;">${article.subject}</a>
                                <c:if test="${cnum[status.index].count != 0}">
                    			&nbsp;&nbsp;&nbsp;${cnum[status.index].count}
                    			</c:if>
                                </td>
                                <td>${article.name}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${article.postDate}"/></td>
                                <td>${article.views}</td>
                                <td>${article.likes}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card col-12 mb-3" id="newboard">
                <div class="card-body">
                    <h5><b>인기 게시글</b></h5>
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
                        <c:forEach var="article" items="${boardListPop}" varStatus="status">
                            <tr>
                                <td>${article.idx}</td>
                                <td style="font-size: 10px;"><a href="/boardDetail?idx=${article.idx}" style="font-size: medium; text-decoration: none;">${article.subject}</a>
                                <c:if test="${cnumPop[status.index].count != 0}">
                    			&nbsp;&nbsp;&nbsp;${cnumPop[status.index].count}
                    			</c:if>
                                </td>
                                <td>${article.name}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${article.postDate}"/></td>
                                <td>${article.views}</td>
                                <td>${article.likes}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</t:layout>
