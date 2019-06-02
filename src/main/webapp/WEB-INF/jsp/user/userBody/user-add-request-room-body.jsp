<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:setBundle basename="messages" var="messages"/>
<!DOCTYPE html>

<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>

<c:set var='error' value="${Error}"/>
<div class="col w-100">
    <c:if test="${error !=null}">
        <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100" role="alert">
                ${error}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="w-100 d-none d-md-block"></div>
    </c:if>
</div>

<h1><fmt:message key="local.user.add.request.room" bundle="${messages}"/></h1>
<form id="form" method="post" action="user-add-request-room" class="needs-validation" novalidate>

    <label for="user_id"><fmt:message key="local.user.request.room.user.id" bundle="${messages}"/></label>
    <input required type="text" class="form-control" id="user_id" name="user_id" size="1"
           readonly
           value=${sessionScope.user_id}>
    <div class="form-group">
        <label for="placeNumber"><fmt:message key="local.user.add.request.room.placeNumber" bundle="${messages}"/></label>
        <select name="placeNumber" id="placeNumber" class="form-control" title="placeNumber" required="required">
            <option selected><fmt:message key="local.user.add.request.room.placeNumber.placeholder" bundle="${messages}"/></option>
            <c:forEach var="place" items="${placesNumber}">
                 <option value=${place}>${place} </option>
                ${place}
            </c:forEach>
        </select>
        <label for="apartmentClass"><fmt:message key="local.user.add.request.room.apartmentClass" bundle="${messages}"/></label>
        <select name="apartmentClass" id="apartmentClass" class="form-control" title="apartmentClass" required="required">
            <option selected><fmt:message key="local.user.add.request.room.apartmentClass.placeholder" bundle="${messages}"/></option>
            <c:forEach var="apartmentClass" items="${apartmentsClass}">
                  <option value=${apartmentClass}>${apartmentClass} </option>
                ${apartmentClass}
             </c:forEach>
        </select>

        <label for="arrivalDate"><fmt:message key="local.user.request.room.arrival.date" bundle="${messages}"/></label>
        <input required type="date" class="form-control" id="arrivalDate" name="arrivalDate"
               placeholder="<fmt:message key="local.user.request.room.arrival.date.placeholder" bundle="${messages}"/>">

        <label for="departureDate"><fmt:message key="local.user.add.request.room.departure.date" bundle="${messages}"/></label>
        <input required type="date" class="form-control" id="departureDate" name="departureDate"
               placeholder="<fmt:message key="local.user.add.request.room.departure.date.placeholder" bundle="${messages}"/>">

        <label for="status"><fmt:message key="local.user.add.request.room.status" bundle="${messages}"/></label>
        <input required type="text" class="form-control" id="status" name="status"
               readonly
               value='NEW'>

    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="local.admin.add.room.button.add"
                                                               bundle="${messages}"/></button>
    <%--  <a href="${pageContext.request.contextPath}/main/index" class="btn btn-primary">Main</a>--%>
</form>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();

    $(function () {
        window.setTimeout(function () {
            $('#my-alert').alert('close');
        }, 20000);
    });
</script>

</body>
</html>
