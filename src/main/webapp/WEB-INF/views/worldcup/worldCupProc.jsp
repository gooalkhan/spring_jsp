<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <style>
    	*{
    	background: black;
    	}
    </style>
<title>${data.subject}</title>
</head>
<body>
	<c:forEach items="${image}" var="img">
		<div class="iN" style="display: none;">${img.imageName}</div>
		<div class="oIN" style="display: none;">${img.originImageName}</div>
	</c:forEach>
		<div id="dataSubject" style="display: none;">${data.subject}</div>
    <div class="container-fluid">
    <h3 class="text-center" id="worldCupSubject" style="background: rgb(128,128,128);">${data.subject}</h3>
        <div class="row align-items-center">
            <div class="col-6" onclick="winImageL()" id="leftImageContainer">
                <img src="" class="img-fluid mx-auto d-block" id="leftImage" alt="이미지에 오류나서 안 보이는 거임">
                <p class="text-center" id="leftImageName" style="color: white"></p>
            </div>
            <div class="col-6" onclick="winImageR()" id="rightImageContainer">
                <img src="" class="img-fluid mx-auto d-block" id="rightImage" alt="이미지에 오류나서 안 보이는 거임">
                <p class="text-center" id="rightImageName" style="color: white"></p>
            </div>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/worldcup.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script>
	startGame();
</script>
</html>