<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                                                               value="${selectedBook.series_last_update}"/></p>


                <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#description-modal">
                    작품 소개글 보기
                </button>
                <c:if test="${not detail}">
                    <button class="btn btn-success" type="button" onclick="location.href='/books/bookDetail?id=${id}'">
                        분석 보기
                    </button>
                </c:if>

                <button class="btn btn-primary" type="button"
                        onclick="window.open('https://ridibooks.com/books/${selectedBook.bookid}')">보러 가기
                </button>

                <!-- Modal -->
                <div class="modal modal-lg fade" id="description-modal" tabindex="-1"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
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