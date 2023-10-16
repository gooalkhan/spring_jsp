<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<t:layout>
    <div class="container py-3">
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
                    <div class="card col-4 mx-3" style="width: 18rem;">
                        <img src="${pageContext.request.contextPath}/resources/images/treasure.png" class="card-img-top"
                             alt="...">
                        <div class="card-body">
                            <h5 class="card-title text-center"><b>100 포인트 구입</b></h5>
                            <p class="card-text">처음 오시는 유저에게 추천</p>
                            <div class="text-center">
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#hundred" aria-expanded="false"
                                        aria-controls="hundred">
                                    구매하기
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card col-4 mx-3" style="width: 18rem;">
                        <img src="${pageContext.request.contextPath}/resources/images/treasure.png" class="card-img-top"
                             alt="...">
                        <div class="card-body">
                            <h5 class="card-title text-center"><b>500 포인트 구입</b></h5>
                            <p class="card-text">5% 포인트를 추가로 드립니다</p>
                            <div class="text-center">
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#five-hundred" aria-expanded="false"
                                        aria-controls="five-hundred">
                                    구매하기
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card col-4 mx-3" style="width: 18rem;">
                        <img src="${pageContext.request.contextPath}/resources/images/treasure.png" class="card-img-top"
                             alt="...">
                        <div class="card-body">
                            <h5 class="card-title text-center"><b>1000 포인트 구입</b></h5>
                            <p class="card-text">10% 포인트를 추가로 드립니다</p>
                            <div class="text-center">
                                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#thousand" aria-expanded="false"
                                        aria-controls="thousand">
                                    구매하기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="purchase-group">
                    <div id="accordion-group">
                        <div class="collapse" id="hundred" data-bs-parent="#purchase-group">
                            <hr>
                            <div class="card card-body mb-3 p-3">
                                <p class="card-text">구매 방법</p>
                                <form class="form-control p-5" method="post">
                                    <div class="row justify-content-center mb-3">
                                        <div class="col-auto form-check">
                                            <input class="form-check-input" type="radio" name="purchase-method"
                                                   id="kakaopay" checked>
                                            <label class="form-check-label" for="kakaopay">
                                                <img src="${pageContext.request.contextPath}/resources/images/img-kakaopay.png"
                                                     alt="kakaopay">
                                            </label>
                                        </div>
                                        <div class="col-auto form-check">
                                            <input class="form-check-input" type="radio" name="purchase-method"
                                                   id="naverpay">
                                            <label class="form-check-label" for="naverpay">
                                                <img src="${pageContext.request.contextPath}/resources/images/img-naverpay.png"
                                                     alt="naverpay">
                                            </label>
                                        </div>
                                        <div class="col-auto form-check">
                                            <input class="form-check-input" type="radio" name="purchase-method"
                                                   id="payco">
                                            <label class="form-check-label" for="payco">
                                                <img src="${pageContext.request.contextPath}/resources/images/img-payco.png"
                                                     alt="payco">
                                            </label>
                                        </div>
                                        <div class="col-auto form-check">
                                            <input class="form-check-input" type="radio" name="purchase-method"
                                                   id="credit-card">
                                            <label class="form-check-label" for="credit-card">
                                                신용카드
                                            </label>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirm-buy">
                                        구매 하기
                                        </button>
                                    </div>

                                    <!-- Modal -->
                                    <div class="modal fade" id="confirm-buy" tabindex="-1" aria-labelledby="confirm-buy-label" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="confirm-buy-label">확인</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    구입을 확정하시겠습니까?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                                    <input type="submit" class="btn btn-primary" value="확인">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>


                            </div>
                        </div>

                        <div class="collapse" id="five-hundred" data-bs-parent="#purchase-group">
                            <hr>
                            <div class="card card-body mb-3 p-3">
                                <p class="card-text">구매 방법</p>
                            </div>
                        </div>

                        <div class="collapse" id="thousand" data-bs-parent="#purchase-group">
                            <hr>
                            <div class="card card-body mb-3 p-3">
                                <p class="card-text">구매 방법</p>
                            </div>
                        </div>
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