<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<div class="container py-3">
	    <div class="row justify-content-center">
            <div class="m-3 text-center">
                <h3>가입할 당시의 이메일을 정확하게 적어주세요</h3>
            </div>
        </div>
        <form method="Post">
        <div class="d-grid gap-3">
        	<span class="row justify-content-center">이메일</span>
            <div class="container d-flex justify-content-center" style="width: 200px;">
        		<input type="email" class="form-control" name="email" Placeholder="이메일 입력" required>
        	</div>
        	<div class="container d-flex justify-content-center" style="width: 200px;">
        		<input type="submit" class="btn btn-outline-success" value="아이디 찾기">
        	</div>
        </div>
        </form>
	</div>	
</t:layout>