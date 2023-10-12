<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:layout>
    <div class="container py-3">
        <div class="row justify-content-center">
            <div class="col-4 m-3 text-center">
                <h3>회원 가입</h3>
            </div>
        </div>
        <div class="container">
            <form name=memberjoinform onsubmit="return validateMemberForm('join')" method="Post">
                <div class="row justify-content-center mb-3">
                    <div class="col-5">
                        <div class="mb-3">
                            <label for="id">아이디</label>
                            <input type="text" id="id" class="form-control" name="id" placeholder="아이디" required>
                        </div>
                        <div class="mb-3">
                            <label for="pw">비밀번호</label>
                            <input type="password" id="pw" class="form-control" name="pw" placeholder="비밀번호" required>
                        </div>
                        <div class="mb-3">
                            <label for="pw2">비밀번호(재입력)</label>
                            <input type="password" id="pw2" class="form-control" name="pw2" placeholder="비밀번호" required>
                        </div>
                        <div class="mb-3">
                            <label for="email">이메일</label>
                            <input type="email" id="email" class="form-control" name="email" placeholder="이메일">
                        </div>
                        <div class="mb-3">
                            <label for="name">이름</label>
                            <input type="text" id="name" class="form-control" name="name" placeholder="이름">
                        </div>
                    </div>
                    <div class="container d-flex justify-content-center">
                        <input type="submit" class="btn btn-primary" value="가입 하기">
                    </div>
                </div>
            </form>
        </div>
    </div>
</t:layout>
