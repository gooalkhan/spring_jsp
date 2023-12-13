<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container py-3">
        <form method="Post" enctype="multipart/form-data">
        	<input type="hidden" name="membertbl_id" value="${sid}">
            <div class="mb-3">
                <label for="idx" class="form-label">게시글 번호</label>
                <input type="text" class="form-control" id="idx" value="${data.idx}" readonly disabled>
            </div>
            <div class="mb-3">
                <label for="subject" class="form-label">제목</label>
                <input type="text" class="form-control" id="subject" name="subject" value="${data.subject}" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" required>${data.content}</textarea>
            </div>
            <div class="mb-3">
                <label for="subject">이미지 첨부(제한 용량 : 100MB)</label>
                <input type='file' class='form-control' name='uploadfile' id='uploadfile' accept='.jpg,.jpeg,.bmp,.png,.gif' multiple>
            </div>
            <div class="mb-3" id="imagePreview"></div>
            <p id="message"></p>
            <div class="container d-flex justify-content-center">
                <input type="submit" class="btn btn-primary" value="변경사항저장">
            </div>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/resources/js/imageLimit.js"></script>
</t:layout>