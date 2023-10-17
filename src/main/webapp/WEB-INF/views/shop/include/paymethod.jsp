<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-auto form-check mb-3">
    <input class="form-check-input" type="radio" value="kakaopay" name="purchaseMethodUID"
           id="kakaopay" checked>
    <label class="form-check-label" for="kakaopay">
        <img src="${pageContext.request.contextPath}/resources/images/img-kakaopay.png"
             alt="kakaopay">
    </label>
</div>
<div class="col-auto form-check mb-3">
    <input class="form-check-input" type="radio" value="naverpay" name="purchaseMethodUID"
           id="naverpay">
    <label class="form-check-label" for="naverpay">
        <img src="${pageContext.request.contextPath}/resources/images/img-naverpay.png"
             alt="naverpay">
    </label>
</div>
<div class="col-auto form-check mb-3">
    <input class="form-check-input" type="radio" value="payco" name="purchaseMethodUID"
           id="payco">
    <label class="form-check-label" for="payco">
        <img src="${pageContext.request.contextPath}/resources/images/img-payco.png"
             alt="payco">
    </label>
</div>
<div class="col-auto form-check mb-3">
    <input class="form-check-input" type="radio" value="creditcard" name="purchaseMethodUID"
           id="credit-card">
    <label class="form-check-label" for="credit-card">
        신용카드
    </label>
</div>