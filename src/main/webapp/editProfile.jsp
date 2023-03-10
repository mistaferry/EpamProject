<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>
        <fmt:message key="company.name"/>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@ include file="/parts/css/newStylr.css"%>
    </style>
</head>

<%@ include file="parts/userMenuChoose.jspf" %>

<form method="post" action="controller">
    <input type="hidden" name="action" value="edit-user-profile">
    <div class="info">
        <div>
            <h1><fmt:message key="edit.profile"/></h1>
        </div>
        <div>
            <c:set var="userName"
                   value="${sessionScope.prevUser.firstName eq null ? sessionScope.user.firstName : sessionScope.prevUser.firstName}"/>
            <c:set var="userSurname"
                   value="${sessionScope.prevUser.surname eq null ? sessionScope.user.surname : sessionScope.prevUser.surname}"/>
            <c:set var="userLogin"
                   value="${sessionScope.prevUser.login eq null ? sessionScope.user.login : sessionScope.prevUser.login}"/>
            <h4><fmt:message key="label.person.name"/></h4>
            <label>
                <input type="text" name="firstName" value="${userName}"/>
            </label>
            <h4><fmt:message key="label.person.surname"/></h4>
            <label>
                <input type="text" name="surname" value="${userSurname}"/>
            </label>
            <h4><fmt:message key="label.person.login"/></h4>
            <label>
                <input type="text" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}" name="login"
                       value="${userLogin}"/>
            </label>
            <c:if test="${sessionScope.error ne null}">
                <span class="error-text"><fmt:message key="${sessionScope.error}"/></span>
                <%session.removeAttribute("error"); %>
            </c:if>
        </div>
        <br>
        <button id="form-button" type="submit"><fmt:message key="button.save.changes"/></button>
    </div>
</form>

<%@ include file="/parts/footer.jspf" %>

</html>