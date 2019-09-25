<%@ page import="java.io.OutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

    <style type="text/css">
        body{
            height: 200px; /* Высота блока */
            /*border: 2px solid #000; !* Параметры рамки *!*/
            <%--background: url("${contextPath}/resources/images/barber.jpeg") 100% 100% no-repeat; /* Добавляем фон */--%>
            background-size: cover; /* Масштабируем фон */
        }
    </style>

    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script defer src="${contextPath}/resources/fontawesome-free-5.6.1-web/js/all.min.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name" /></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav ">
            <li class="nav-item">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                <span class="navbar-text">
                Login as:
                </span>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <strong><security:authentication property="principal.username"/></strong>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="<c:url value="/logout"/>">Выйти</a>
                    </div>
                </li>
            </sec:authorize>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center" >
    <div class="row">
        <div class="col-6">
            <ul class="list-group">
                <script>var count = 1;</script>
                <c:forEach items="${sessionScope.questionList}" var="questionList">

                    <a href="<c:url value='/getQuestion-${questionList.id}' />" class="list-group-item list-group-item-action"><script>document.write(count++)</script></a>

                </c:forEach>
            </ul>
        </div>
        <div class="col-6">
            <c:choose>
                <c:when test= "${questionList[0].questionType.name == 'ONE'}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                        <label class="form-check-label" for="defaultCheck1">
                            Default checkbox
                        </label>
                    </div>

                </c:when>
                <c:otherwise>
                    do this when nothing else is true
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/?lang=' + selectedOption);
            }
        });
    });
</script>

</body>
</html>
