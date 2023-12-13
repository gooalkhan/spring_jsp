<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <h1 class="text-center">월드컵의 승자는...!!!!!!!!!</h1>
        <div class="row my-3">
            <img src="" class="img-fluid mx-auto d-block" id="winnerImg" alt="이미지가 안 보인다면, 월드컵을 진행해주세요." style="max-height: 700px; width: auto;">
        </div>
        <h3 class="text-center" id="winnerImgName"></h3>
        <script src="${pageContext.request.contextPath}/resources/js/worldcup.js"></script>
        <script>
            endGame();
        </script>
    </div>
</t:layout>