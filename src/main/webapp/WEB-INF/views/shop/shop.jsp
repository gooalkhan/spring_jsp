<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<t:layout>
    <div class="container py-3">
        <c:if test="${not empty success}">
        <c:choose>
        <c:when test="${success=='true'}">
        <div class="alert alert-success" role="alert">
            포인트 구입에 성공하였습니다.
        </div>
        </c:when>
        <c:otherwise>
        <div class="alert alert-danger" role="alert">
            포인트 구입에 실패하였습니다.
        </div>
        </c:otherwise>
        </c:choose>
        </c:if>
        <div class="row justify-content-center">
            <div class="col-4 m-3 text-center">
                <h3>포인트 샵</h3>
            </div>
        </div>
        <c:choose>
        <c:when test="${not empty sname}">
        <div class="row justify-content-center">
            <div class="col m-3 text-center">
                    <%-- 포인트샵 테이블 구현할것 --%>
                <h5> ${sname} 회원님의 포인트는 현재 ${point} 포인트 입니다</h5>
            </div>
        </div>
        <div class="row justify-content-center">
            <c:forEach var="offer" items="${currentCampaigns}" varStatus="status">
                <div class="card col-4 m-3" style="width: 18rem;">
                    <img src="${pageContext.request.contextPath}/resources/images/treasure.png" class="card-img-top"
                         alt="${offer.campaignName}">
                    <div class="card-body">
                        <h5 class="card-title text-center"><b>${offer.point} 포인트 구입</b></h5>
                        <p class="card-text">${offer.campaignDescription}</p>
                        <div class="text-center">
                            <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#${offer.uid}" aria-expanded="false"
                                    aria-controls="${offer.uid}">
                                구매하기
                            </button>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div id="purchase-group">
                <div id="accordion-group">
                    <c:forEach var="offer" items="${currentCampaigns}" varStatus="status">
                        <div class="collapse" id="${offer.uid}" data-bs-parent="#purchase-group">
                            <hr>
                            <div class="card card-body mb-3 p-3">
                                <p class="card-text">구매 방법</p>
                                <form class="form-control p-5" action="/shop/purchase" method="post">
                                    <input type="hidden" name="campaignUID" value="${offer.uid}">
                                    <input type="hidden" name="userid" value="${sid}">
                                    <input type="hidden" name="addedPoint" value="${offer.point}">
                                    <div class="row justify-content-center mb-3">
                                        <jsp:include page="include/paymethod.jsp"/>
                                    </div>
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                                data-bs-target="#confirm-buy-${offer.uid}">
                                            구매 하기
                                        </button>
                                    </div>

                                    <!-- Modal -->
                                    <div class="modal fade" id="confirm-buy-${offer.uid}" tabindex="-1"
                                         aria-labelledby="confirm-buy-label" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="confirm-buy-label">확인</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                        ${offer.point} 포인트 구입을 확정하시겠습니까?
                                                    <br>
                                                        ${offer.additionalPoint} 포인트가 추가로 적립됩니다.
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">취소
                                                    </button>
                                                    <input type="submit" class="btn btn-primary" value="확인">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>


                    </c:forEach>
                </div>
            </div>
            </c:when>
            <c:otherwise>
                <div class="row justify-content-center">
                    <div class="col-4 m-3 text-center">
                        <h5>포인트 샵은 로그인 후 이용 가능합니다.</h5>
                    </div>
                </div>
            </c:otherwise>
            </c:choose>


        </div>
</t:layout>