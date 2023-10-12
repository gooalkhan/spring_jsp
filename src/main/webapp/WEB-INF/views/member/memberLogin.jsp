<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <div class="container py-3">
        <div class="row justify-content-center">
            <div class="col-4 m-3 text-center">
                <h3>로그인</h3>
            </div>
        </div>
        <form method="Post">
            <div class="row justify-content-center">
                <div class="col-5 mb-3">
                    <div class="mb-1">
                        <label for="id">아이디</label>
                        <input type="text" class="form-control" id="id" name="id" placeholder="아이디"></p>
                    </div>
                    <div class="mb-1">
                        <label for="pw">비밀번호</label>
                        <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호">
                    </div>
                </div>
            </div>
            <div class="container d-flex justify-content-center">
                <input type="submit" class="btn btn-primary" value="로그인">
            </div>
        </form>
    </div>
</t:layout>