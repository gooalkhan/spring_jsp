<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:layout>
    <div class="container py-3">
        <div class="row justify-content-center">
            <div class="col m-3 text-center">
                <h3>${title} 분석</h3>
            </div>
        </div>
        <div class="container">
            <c:import url="/books/bookcard?bookid=${id}&detail=true"/>
            <hr>
            <c:import url="/books/analysis/keyword?bookid=${id}"/>

            <c:import url="/books/analysis/favorite?bookid=${id}"/>

        </div>
    </div>
</t:layout>