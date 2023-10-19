<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card mb-3 p-3">
    <p class="card-text"> <h5><b>${analysis} 분석</b></h5> </p>
    <div class="container d-flex justify-content-center">
        <form id="purchase" name="keyword-purchase" onsubmit="return purchaseAnalysis()" action="/keyword" method="post">
            <input hidden value="${productDTO.bookid}" name="bookid">
            <input hidden value="${productDTO.uid}" name="uid">
            <input hidden value="${productDTO.userid}" name="userid">
            <input hidden value="${productDTO.productid}" name="productid">
            <input hidden value="${productDTO.usedPoint}" name="usedPoint">
            <input type="submit" class="btn btn-primary" value="구입">
        </form>
    <button class="btn btn-warning" onclick="getKeywordAnalysis(${bookid},'${analysis}')">ajax가져오기</button>
    </div>
    <div class="container">
        <p class="card-text" id="analysis"></p>
    </div>
</div>