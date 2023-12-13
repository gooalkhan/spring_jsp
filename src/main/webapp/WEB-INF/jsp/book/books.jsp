<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <div class="container py-3">
        <div class="row justify-content-center">
            <div class="col-4 m-3 text-center">
                <h3>웹소설 작품별 분석 자료</h3>
            </div>
        </div>
        <div class="container">
            <c:if test="${not empty id}">
                <c:import url="/books/bookcard?bookid=${id}"/>
            </c:if>
        </div>
        <c:if test="${not empty id}">
            <hr>
            <div class="container mb-6">
                <p>다른 작품</p>
            </div>
        </c:if>
        <div class="row">
            <c:forEach var="book" items="${data}" varStatus="status">
                <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2 mb-3 px-2">
                    <div class="card" style="width: 8rem">
                        <a href="?page=${page}&id=${book.bookid}&condition=${param.condition}&searchword=${param.searchword}">
                            <img src="https://img.ridicdn.net/cover/${book.bookid}/xlarge" class="img-fluid rounded-start"
                                 alt="${book.bookid}">
<%--                            <svg class="card-img-top book-pic" width="100%" height="200">--%>
<%--                                <text x="30" y="100">--%>
<%--                                        ${book.bookid}--%>
<%--                                </text>--%>
<%--                                <text x="30" y="120">--%>
<%--                                    표지--%>
<%--                                </text>--%>
<%--                                <rect width="100%" height="100%" fill="blue"--%>
<%--                                      fill-opacity="0.3"/>--%>
<%--                            </svg>--%>
                        </a>
                        <div class="card-body book-card">
                            <div class="row">
                                <div class="col-12">
                                    <h6 class="card-title">${book.title}</h6></div>
                                <div class="col-12"><p class="card-text"><small>${book.author}</small></p></div>
                                <div class="col-12"><p class="card-text"><small>${book.publisher}</small></p></div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="container d-flex justify-content-center">
            <form name="bookSearch" method="get">
                <div class="row">
                    <div class="col-auto">
                        <select class="form-select" name="condition">
                            <option value="title">제목</option>
                            <option value="author">작가</option>
                            <option value="publisher">출판사</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <input type="text" class="form-control" id="searchbox" name="searchword"
                               placeholder="검색어를 입력해주세요"/>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="pagination justify-content-center my-3">
            <ul class="pagination pagination-lg">
                <li class="page-item ${page == 1 ? 'disabled' : ''}"><a class="page-link"
                                                                        href="?page=${page - 1}&id=${not empty id ? id : 0}&condition=${param.condition}&searchword=${param.searchword}">이전</a>
                </li>
                <c:forEach var="i" begin="${page -2 <= 0 ? 1 : page - 2 }"
                           end="${page + 2 > pageCount ? pageCount : page+2}" varStatus="status">
                    <li class="page-item ${status.index == page ? 'active' : ''}"><a class="page-link"
                                                                                     href="?page=${status.index}&id=${not empty id ? id : 0}&condition=${param.condition}&searchword=${param.searchword}">${status.index}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${page == pageCount ? 'disabled' : ''}"><a class="page-link"
                                                                                href="?page=${page + 1}&id=${not empty id ? id : 0}&condition=${param.condition}&searchword=${param.searchword}">다음</a>
                </li>
            </ul>
        </div>
    </div>
</t:layout>