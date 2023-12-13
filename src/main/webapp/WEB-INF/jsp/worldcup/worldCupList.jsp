<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <div class="row mb-3">
            <c:forEach var="data" items="${data}" varStatus="status">
                <div class="card col-4 p-3 ms-3 mt-3">
                    <a href="${pageContext.request.contextPath}/worldCupProc?idx=${data.idx}" class="card-text h4"
                       style="text-decoration: none; color: #000000; font-weight: bold;"><img
                            src="${pageContext.request.contextPath}/worldcupimages/${image[status.index].imageName}" alt="이미지"
                            class="img-fluid mx-auto d-block" style="max-height: 300px;"></a>
                    <div class="card-body px-0">
                        <a href="${pageContext.request.contextPath}/worldCupProc?idx=${data.idx}" class="card-text h4"
                           style="text-decoration: none; color: #000000; font-weight: bold;">${data.subject}</a>
                        <div class="card-text mt-2 small">${data.content}</div>
                    </div>
                    <c:if test="${data.membertbl_id == sid || sadmin == 'admin' || sadmin == 'subadmin'}">
                        <form method="Post" action="${pageContext.request.contextPath}/worldCupDelete" class="text-center">
                            <input type="hidden" name="idx" value="${data.idx}">
                            <input type="submit" class="btn btn-danger" value="삭제">
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <hr>
        <div class="container">
            <div class="row justify-content-around">
                <div class="col-md-4 col-lg-auto mb-3 text-center">
                    <a href="${pageContext.request.contextPath}/myWorldCupList" class="btn btn-success">내 월드컵 보러가기</a>
                </div>
                <div class="col-md-5 col-lg-auto mb-3 text-center">
                    <a href="${pageContext.request.contextPath}/worldCupCreate" class="btn btn-primary">이상형 월드컵 만들러 가기</a>
                </div>
                <div class="col-md-3 col-lg-auto mb-3 text-center">
                    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">메인으로</a>
                </div>

            <div class="col-md-12 col-lg-auto text-center">
            <form action="${pageContext.request.contextPath}/worldCupSearch">
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
    </div>
</t:layout>