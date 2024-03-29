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
<div class="container d-flex justify-content-center">
    <div class="row">
        <div class="col-6">
            <ul class="list-group">
                <script>
                    var count = 1;
                    var questionsIdsArray = new Array();
                </script>
                <button type="button" name="selectedButton" onclick="clickQuestion(this);return false;" value="${questionList[0].id}" class="btn btn-primary disabled mb-1" ]><script>document.write(count++)</script></button>
                <script>questionsIdsArray.push(${questionList[0].id})</script>
                <c:forEach begin="1" items="${sessionScope.questionList}" var="questionList">
                    <button type="button" name=" " onclick="clickQuestion(this);return false;" value="${questionList.id}" class="btn btn-primary mb-1" ]><script>document.write(count++)</script></button>
                    <script>questionsIdsArray.push(${questionList.id})</script>
                </c:forEach>
            </ul>
        </div>
        <div class="col-6">
            <div id="currentQuestion">
                ${questionList[0].definition}
            <c:choose>
                <c:when test= "${questionList[0].questionType.name == 'MULTIPLE'}">
                <div>
                    <c:forEach items="${questionList[0].answers}" var="answer">
                        <input type="checkbox" id="contactChoice${answer.id}" class="get_value" value="${answer.id}">
                        <label for="contactChoice${answer.id}">${answer.definition}</label>
                    </c:forEach>
                </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${questionList[0].answers}" var="answer">
                        <input type="radio" name="radioAnswer" id="contactChoice${answer.id}" value="${answer.id}">
                        <label for="contactChoice${answer.id}">${answer.definition}</label>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </div>
            <button type="button" name="submit" class="btn btn-info" id="submit">Ответить</button>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <a href="/results" class="btn btn-danger btn-lg active" role="button" aria-pressed="true">Закончить тест</a>
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
<script>
    $(document).ready(function () {
            $('#submit').click(function () {
                repaintQuestion();
            });
    });

    function selectNextButton(radioAnswer, questionId) {
        var selectedQuestion = $( "button[name='selectedButton']" );
        var questionID = parseInt(selectedQuestion.val());
        var index = questionsIdsArray.indexOf(questionID);
        var nextIndex;
        if (index < questionsIdsArray.length-1){
            nextIndex = index + 1;
        } else {
            nextIndex = 0;
        }
        var nextQuestionId = questionsIdsArray[nextIndex];
        var nextSelectedQuestion = $( "button[value="+nextQuestionId+"]" );

        ajaxSend("/radioAnswered", radioAnswer, questionId, nextQuestionId);


        selectedQuestion.attr("name", " ");
        selectedQuestion.removeClass("disabled");

        nextSelectedQuestion.attr("name", "selectedButton");
        nextSelectedQuestion.addClass("disabled");
    }

    function repaintQuestion() {
        var idViaRadioChoice = $( "input[name='radioAnswer']" ).val();
        if(idViaRadioChoice != undefined){
            var radioAnswer = $( "input[name='radioAnswer']:checked" ).val();
            var questionID = $( "button[name='selectedButton']" ).val();
            selectNextButton(radioAnswer, questionID);
        } else {
            var answers = [];
            $('.get_value').each(function () {
                if ($(this).is(":checked")) {
                    answers.push($(this).val());
                }
            });
            ajaxSend("/checkboxAnswered", answers)
        }
    }

    function ajaxSend(url, answerID, questionID, nextQuestionID) {
        $.ajax({
            type: "POST",
            url: url,
            data: "answerID="+answerID+"&questionID="+questionID+"&nextQuestionID="+nextQuestionID,
            success: function (data) {
                addQuestionToView(data);
            }
        });
    }

    function getQuestionAjax(url) {
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json",
            success: function (data) {
                addQuestionToView(data);
            }
        });
    }
    function clickQuestion(obj) {
        var previousSelectedQuestion = $( "button[name='selectedButton']" );
        previousSelectedQuestion.attr("name", " ");
        previousSelectedQuestion.removeClass("disabled");

        $(obj).attr("name", "selectedButton");
        $(obj).addClass("disabled");

        getQuestionAjax("/getQuestion-" + $(obj).val())

    }

    function addQuestionToView(data) {
        var answeredQuestion = false;
        var select = $('#currentQuestion').empty();

        select.append(data.definition);
        if (data.questionType === 'ONE'){
            $.each(data.answers, function (i, item) {
                if (item.answered == true){
                    answeredQuestion = true;
                    return false;
                }
            });
            $.each(data.answers, function (i, item) {
                if (answeredQuestion){
                    if (item.answered) {
                        select.append('<input type="radio" name="radioAnswer" id="contactChoice' + item.id + '" value="' + item.id + '" checked="checked" disabled>' +
                            '<label for="contactChoice' + item.id + '">' + item.definition + '</label>');
                    } else {
                        select.append('<input type="radio" name="radioAnswer" id="contactChoice' + item.id + '" value="' + item.id + '" disabled>' +
                            '<label for="contactChoice' + item.id + '">' + item.definition + '</label>');
                    }
                } else {
                    select.append('<input type="radio" name="radioAnswer" id="contactChoice' + item.id + '" value="' + item.id + '">' +
                        '<label for="contactChoice' + item.id + '">' + item.definition + '</label>');
                }
            });
        } else {
            $.each(data.answers, function (i, item) {
                select.append('<input type="checkbox" id="contactChoice'+item.id+'" class="get_value" value="'+item.id+'">'+
                '<label for="contactChoice'+item.id+'">'+item.definition+'</label>');
            })
        }
    }
</script>
