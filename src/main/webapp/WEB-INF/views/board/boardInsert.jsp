<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <div class="row text-center my-3">
            <h3>게시글 등록</h3>
        </div>
        <form method="Post" action="boardInsertPost">
            <input type="hidden" name="membertbl_id" value="${sid}">
            <div class="mb-3">
                <label for="subject">제목</label>
                <input type="text" class="form-control" id="subject" name="subject">
            </div>
            <div class="mb-3">
                <label for="content">내용</label>
                <textarea class="form-control" id="content" rows="8" name="content"></textarea>
            </div>
            <div class="container d-flex justify-content-center">
            <input type="submit" class="btn btn-primary" value="글 등록">
            </div>
        </form>
    </div>
</t:layout>