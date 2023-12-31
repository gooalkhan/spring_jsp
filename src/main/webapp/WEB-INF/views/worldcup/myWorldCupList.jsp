<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <c:if test="${sid != null}">
            <h3 class="text-center mb-3" style="font-weight: bold">내 이상형 월드컵 목록</h3>
            <div class="row mb-3">
                <c:forEach var="data" items="${data}" varStatus="status">
                    <div class="card col-4 p-3 ms-3 mt-3">
                        <a href="/worldCupProc?idx=${data.idx}" class="card-text h4"
                           style="text-decoration: none; color: #000000; font-weight: bold;"><img
                                src="/worldcupimages/${image[status.index].imageName}" alt="이미지"
                                class="img-fluid mx-auto d-block" style="max-height: 300px;"></a>
                        <div class="card-body px-0">
                            <a href="/worldCupProc?idx=${data.idx}" class="card-text h4"
                               style="text-decoration: none; color: #000000; font-weight: bold;">${data.subject}</a>
                            <div class="card-text mt-2 small">${data.content}</div>
                        </div>
                        <form method="Post" action="/worldCupDelete" class="text-center">
                            <input type="hidden" name="idx" value="${data.idx}">
                            <input type="submit" class="btn btn-danger" value="삭제">
                        </form>
                    </div>
                </c:forEach>
            </div>
            <hr>
            <div class="container">
                <div class="row justify-content-around">
                <div class="col-md-5 col-lg-auto mb-3 text-center">
                    <a href="/worldCupList" class="btn btn-outline-success">이상형 월드컵 리스트로 가기</a>
                </div>
                <div class="col-md-5 col-lg-auto mb-3 text-center">
                    <a href="/worldCupCreate" class="btn btn-primary">이상형 월드컵 만들러 가기</a>
                </div>
                <div class="col-md-6 col-lg-auto mb-3 text-center">
                    <a href="/" class="btn btn-primary">메인으로</a>
                </div>
                <div class="col-md-6 col-lg-auto text-center">
                <form action="myWorldCupSearch">
                    <div class="row justify-content-center">
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
            </div>
        </c:if>
        <c:if test="${sid == null}">
            <div class="container py-3">
                <div class="row text-center my-3">
                    <h3>로그인을 하셔야 이용하실 수 있습니다.</h3>
                </div>
                <div class="container d-flex justify-content-center">
                    <a href="/memberLogin" class="btn btn-primary">로그인 하러 가기</a>
                </div>
            </div>
        </c:if>
    </div>
</t:layout>