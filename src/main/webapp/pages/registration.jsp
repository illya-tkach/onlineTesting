<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>

<head>
    <meta charset="utf-8">
    <title>User Registration Form</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<%--    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">--%>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>

<body>

<div class="jumbotron d-flex align-items-center">
    <div class="container">
            <div class="row justify-content-center">
                <h2 class="form-heading">Registration</h2>
            </div>
            <form:form action="${pageContext.request.contextPath}/registration" method="post" modelAttribute="userForm">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="form-group">
                        <label for="exampleInputFirstName">First name</label>
                        <form:input path="firstName" type="text" class="form-control" id="exampleInputFirstName" aria-describedby="emailHelp" placeholder="Enter first name"/>
                        <form:errors path="firstName" cssClass="error" />
                        <small id="firstNameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="form-group">
                        <label for="exampleInputLastName">Last name</label>
                        <form:input path="lastName" type="text" class="form-control" id="exampleInputLastName" aria-describedby="emailHelp" placeholder="Enter last name"/>
                        <form:errors path="lastName" cssClass="error" />
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <form:input path="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
                        <form:errors path="password" cssClass="error" />
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <form:input path="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"/>
                        <div><form:errors path="email" cssClass="error" /></div>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
            <button type="submit" class="btn btn-primary">Submit</button>
            </div>
            </form:form>
    </div>
</div>

</body>
</html>