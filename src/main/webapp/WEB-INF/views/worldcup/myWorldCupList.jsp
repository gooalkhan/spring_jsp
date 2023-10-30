<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<c:if test="${sid != null}">
	<h1 class="text-center">내 이상형 월드컵 목록</h1>
	<div class="row mb-3">
		<c:forEach var="data" items="${data}" varStatus="status">
			<div class="col-sm-3" style="padding:20px">
				<div>
					<img src="/worldcupimages/${image[status.index].imageName}" alt="이미지" style="width: 100%; height: auto;">
					<a href="/worldCupProc?idx=${data.idx}" class="h4" style="text-decoration: none; color: #000000; font-weight: bold;">${data.subject}</a>
					<div class="small">${data.content}</div>
					<form method="Post" action="/worldCupDelete">
                    	<input type="hidden" name="idx" value="${data.idx}">
                    	<input type="submit" class="btn btn-danger" value="삭제">
                    </form>
				</div>
			</div>
		</c:forEach>
	</div>
        <div class="container d-flex justify-content-around">
            <div class="col-auto">
            	<a href="/myWorldCupList" class="btn btn-success">이상형 월드컵 리스트로 가기</a>
            </div>
        	<div class="col-auto">
            	<a href="/worldCupCreate" class="btn btn-primary">이상형 월드컵 만들러 가기</a>
            </div>
            <div class="col-auto">
            <a href="/" class="btn btn-primary">메인으로</a>
            </div>
            <!-- 나중에 여기에 제목으로 검색하는 거 구현
            <form action="">
                <div class="row">
                    <div class="col-auto">
                        <input type="text" class="form-control" name="subject" placeholder="제목으로 검색">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </div>
            </form>
            -->
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
</t:layout>