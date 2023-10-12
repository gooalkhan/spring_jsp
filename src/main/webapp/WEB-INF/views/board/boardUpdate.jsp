<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <form method="Post">
            <div class="mb-3">
                <label for="idx" class="form-label">게시글 번호</label>
                <input type="text" class="form-control" id="idx" value="${data.idx}" readonly disabled>
            </div>
            <div class="mb-3">
                <label for="subject" class="form-label">제목</label>
                <input type="text" class="form-control" id="subject" name="subject" value="${data.subject}">
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content">${data.content}</textarea>
            </div>
            <div class="mb-3">
                <input type="submit" class="btn btn-primary" value="변경사항저장">
            </div>
        </form>
    </div>
</t:layout>