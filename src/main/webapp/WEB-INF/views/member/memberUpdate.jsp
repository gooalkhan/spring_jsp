<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <div class="row justify-content-center">
            <div class="col-4 m-3 text-center">
                <h3>회원 정보 수정</h3>
            </div>
        </div>
        <c:if test="${sid != null && data.id == sid}">
        <form name="memberUpdateForm" onsubmit="return validateMemberForm('update')" method="Post">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="id" class="form-label">아이디</label>
                        <input type="text" class="form-control" id="id" value="${data.id}" readonly disabled>
                    </div>
                    <div class="mb-3">
                        <label for="pw" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" id="pw" name="pw" value="${data.pw}" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$" required title="비밀번호는 8~20자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.">
                    </div>
                    <div class="mb-3">
                        <label for="pw2" class="form-label">비밀번호(재입력)</label>
                        <input type="password" class="form-control" id="pw2" name="pw2" value="${data.pw}" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$" required title="비밀번호는 8~20자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.">
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">이름</label>
                        <input type="text" class="form-control" id="name" name="name" value="${data.name}" pattern="^[가-힣a-zA-Z0-9]{2,10}$" required title="이름은 특수문자를 포함하지 않은 2~10자리여야 합니다.">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">이메일</label>
                        <input type="email" class="form-control" id="email" name="email" value="${data.email}" required>
                    </div>
                    <div class="container d-flex justify-content-center">
                    <input type="submit" class="btn btn-primary" value="변경사항저장">
                    </div>
                </div>
            </div>
        </form>
        </c:if>
    </div>
</t:layout>