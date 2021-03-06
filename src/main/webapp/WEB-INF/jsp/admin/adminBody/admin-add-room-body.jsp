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

<h1><fmt:message key="local.admin.add.room" bundle="${messages}"/></h1>
<form id="form" method="post" action="admin-add-room" class="needs-validation" novalidate>
    <div class="form-group">
        <label for="placeNumber"><fmt:message key="local.admin.add.room.placeNumber" bundle="${messages}"/></label>
        <select name="placeNumber" id="placeNumber" class="form-control" title="placeNumber" required="required">
            <option selected><fmt:message key="local.admin.add.room.placeNumber.placeholder" bundle="${messages}"/></option>
            <c:forEach var="place" items="${placesNumber}">
                 <option value=${place}>${place} </option>
                ${place}
            </c:forEach>
        </select>
        <label for="apartmentClass"><fmt:message key="local.admin.add.room.apartmentClass" bundle="${messages}"/></label>
        <select name="apartmentClass" id="apartmentClass" class="form-control" title="apartmentClass" required="required">
            <option selected><fmt:message key="local.admin.add.room.apartmentClass.placeholder" bundle="${messages}"/></option>
            <c:forEach var="apartmentClass" items="${apartmentsClass}">
                  <option value=${apartmentClass}>${apartmentClass} </option>
                ${apartmentClass}
             </c:forEach>
        </select>
        <label for="roomNumber"><fmt:message key="local.admin.add.room.roomNumber" bundle="${messages}"/></label>
        <input required type="text" class="form-control" id="roomNumber" name="roomNumber"
               placeholder="<fmt:message key="local.admin.add.room.roomNumber.placeholder" bundle="${messages}"/>"
               pattern="<fmt:message key="regexNumber" bundle="${regexpValidator}"/>">
        <label for="price"><fmt:message key="local.admin.add.room.price" bundle="${messages}"/></label>
        <input required type="text" class="form-control" id="price" name="price"
               placeholder="<fmt:message key="local.admin.add.room.price.placeholder" bundle="${messages}"/>"
               pattern="<fmt:message key="regexNumber" bundle="${regexpValidator}"/>">
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
