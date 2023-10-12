<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <div class="row justify-content-center">
            <div class="col-4 m-3 text-center">
                <h3>회원 정보 수정</h3>
            </div>
        </div>
        <form name="memberUpdateForm" onsubmit="return validateMemberForm('update')" method="Post">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="id" class="form-label">아이디</label>
                        <input type="text" class="form-control" id="id" value="${data.id}" readonly disabled>
                    </div>
                    <div class="mb-3">
                        <label for="pw" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" id="pw" name="pw" value="${data.pw}" required>
                    </div>
                    <div class="mb-3">
                        <label for="pw2" class="form-label">비밀번호(재입력)</label>
                        <input type="password" class="form-control" id="pw2" name="pw2" value="${data.pw}" required>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">이름</label>
                        <input type="text" class="form-control" id="name" name="name" value="${data.name}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">이메일</label>
                        <input type="email" class="form-control" id="email" name="email" value="${data.email}">
                    </div>
                    <div class="container d-flex justify-content-center">
                    <input type="submit" class="btn btn-primary" value="변경사항저장">
                    </div>
                </div>
            </div>
        </form>
    </div>
</t:layout>