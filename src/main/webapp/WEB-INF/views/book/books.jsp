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
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-md-4 ps-3 py-3">
                            <img src="https://img.ridicdn.net/cover/${id}/xxlarge" class="img-fluid rounded-start"
                                 alt="${id}">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <div class="mb-3">
                                    <h3 class="card-title" style="display: inline">${selectedBook.title}</h3>
                                    <c:if test="${selectedBook.is_complete}">
                                        <span class="badge bg-success">완결</span>
                                    </c:if>
                                    <c:if test="${selectedBook.is_gidamu}">
                                        <span class="badge bg-primary">기다리면 무료</span>
                                    </c:if>
                                    <c:if test="${selectedBook.is_adult_only}">
                                        <span class="badge bg-danger">19금</span>
                                    </c:if>
                                </div>
                                <p class="card-text">장르 : ${selectedBook.category}</p>
                                <p class="card-text">작가 : ${selectedBook.author}</p>
                                <p class="card-text">출판사 : ${selectedBook.publisher}</p>
                                <p class="card-text">등록일 : <fmt:formatDate pattern="yyyy년 MM월 dd일"
                                                                           value="${selectedBook.publish_date}"/></p>
                                <p class="card-text">리뷰 수
                                    : ${not empty selectedBook.review_count ? selectedBook.review_count : ""}</p>
                                <p class="card-text">선호작품 수
                                    : ${not empty selectedBook.preference_count ? selectedBook.preference_count : ""}</p>
                                <p class="card-text">총 편 수 : ${not empty selectedBook.series_count ? selectedBook.series_count : ""}</p>
                                <p class="card-text">마지막 업로드 : <fmt:formatDate pattern="yyyy년 MM월 dd일"
                                                                               value="${selectedBook.last_update}"/></p>


                                <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#description-modal">
                                    작품 소개글 보기
                                </button>
                                <button class="btn btn-success" type="button">분석 보기</button>
                                <button class="btn btn-primary" type="button" onclick="window.open('https://ridibooks.com/books/${selectedBook.bookid}')">보러 가기</button>

                                <!-- Modal -->
                                <div class="modal modal-lg fade" id="description-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
<%--                                            <div class="modal-header">--%>
<%--                                                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>--%>
<%--                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                                            </div>--%>
                                            <div class="modal-body">
                                                    ${not empty selectedBook.description ? selectedBook.description : ""}
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="px-3 pb-3">
                        <c:forEach var="keyword" items="${keywords}" varStatus="status">
                            <span class="badge bg-secondary">${keyword.keyword}</span>
                        </c:forEach>
                    </div>
                </div>
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
                <div class="col-sm-6 col-md-4 col-lg-2 mb-4">
                    <div class="card" style="width: 10rem">
                        <a href="?page=${page}&id=${book.bookid}&condition=${param.condition}&searchword=${param.searchword}">
                            <img src="https://img.ridicdn.net/cover/${book.bookid}/xlarge" class="card-img-top book-pic"
                                 alt="${book.title}"></a>
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