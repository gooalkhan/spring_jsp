<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card mb-3 p-3" id='${productDTO.productid}'>
    <p class="card-text"> <h5><b>
    <c:choose>
        <c:when test="${analysis == '선호작품'}">
            선호작품 분석은 구매 가능하나 현재 준비 중으로 추후 업데이트 됩니다.
        </c:when>
        <c:otherwise>
            ${analysis} 분석
        </c:otherwise>
    </c:choose></b></h5> </p>
    <div class="container d-flex justify-content-center">
        <form id="form-${productDTO.uid}" name="form-${productDTO.uid}"
              onsubmit="return purchaseAnalysis('form-${productDTO.uid}')" action="${pageContext.request.contextPath}/analysis"
              method="post">
            <input hidden value="${productDTO.bookid}" name="bookid">
            <input hidden value="${productDTO.uid}" name="uid">
            <input hidden value="${productDTO.userid}" name="userid">
            <input hidden value="${productDTO.productid}" name="productid">
            <input hidden value="${productDTO.usedPoint}" name="usedPoint">

            <div class="text-center">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#confirm-buy-${productDTO.uid}">
                    ${productDTO.usedPoint} 포인트로 구매 하기
                </button>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="confirm-buy-${productDTO.uid}" tabindex="-1"
                 aria-labelledby="confirm-buy-label" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="confirm-buy-label">확인</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            현재 <span class="current-point">${currentPoint}</span> 포인트를 보유하고있습니다.
                            <br>
                            ${productDTO.usedPoint} 포인트를 사용하여 ${analysis} 분석 구입을 확정하시겠습니까?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-bs-dismiss="modal">취소
                            </button>
                            <input type="submit" class="btn btn-primary" data-bs-dismiss="modal" value="확인">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="container">
        <p class="card-text" id="analysis-${productDTO.productid}"></p>
    </div>
</div>