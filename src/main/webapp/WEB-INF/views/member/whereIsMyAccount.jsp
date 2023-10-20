<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<div class="container py-3">
	    <div class="row justify-content-center">
            <div class="m-3 text-center">
                <h3>찾고자하는 것을 선택해주세요</h3>
            </div>
        </div>
        <div class="d-grid gap-3">
            <div class="container d-flex justify-content-center" style="width: 200px;">
        		<a class="btn btn-outline-primary" href="/whereIsMyId">아이디</a>
        	</div>
        	<div class="container d-flex justify-content-center" style="width: 200px;">
        		<a class="btn btn-outline-success" href="/whereIsMyPw">비밀번호</a>
        	</div>
        </div>
	</div>	
</t:layout>