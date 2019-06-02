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
<c:set var='filmSessionDto' value="${filmSessionDto}"/>
<c:set var='currentFilm_id' value="${filterFilmId}"/>


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

    <div class="w-100 justify-content-center">
        <h1><fmt:message key="local.admin.request.treatment" bundle="${messages}"/></h1>
    </div>
</div>

<div class="w-100 d-none d-md-block"></div>

<%--<form method="post" action="admin-session">
    <div class="form-group field-middle_name row">
        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="film_filter"><fmt:message key="local.admin.session.film" bundle="${messages}"/></label>
            <select name="film_filter" id="film_filter" class="form-control" title="Film" required="required">
                <option selected><fmt:message key="local.admin.session.film.choose" bundle="${messages}"/></option>
                <c:forEach var="film" items="${filmsDto}">
                    <c:choose>
                        <c:when test="${ (currentFilm_id !=null && film.getId() == currentFilm_id)}">
                            <option selected="selected" value=${film.getId()}>${film.getName()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value=${film.getId()}>${film.getName() }
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="date_filter"><fmt:message key="local.admin.request.treatment.date" bundle="${messages}"/></label>
            <input required type="date" class="form-control" id="date_filter" name="date_filter"
                   placeholder="Enter session date"
                   value=${filterDate}>
        </div>
    </div>
    <div class="help-block row"></div>

    <button type="submit" class="btn btn-primary my-sm-2"><fmt:message key="local.admin.session.button.find"
                                                                       bundle="${messages}"/></button>--%>
    <%--</form>--%>

    <table class="table table-sm table-striped">
        <thead>
        <tr>
            <th><fmt:message key="local.admin.request.treatment.table.id" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.request.treatment.table.place_number" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.request.treatment.table.class" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.request.treatment.table.arrival_date" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.request.treatment.table.departure_date" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.request.treatment.table.user_id" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.request.treatment.table.user_nickname" bundle="${messages}"/></th>
        </thead>
        <c:forEach var="requestRoom" items="${requestRooms}" varStatus="loop">
            <tr>
                <td>
                    <input type="text" class="form-control" id="request_id_${loop.index}"
                           name="request_id_${loop.index}" size="1"
                           readonly
                           value=" ${requestRoom.getId()}">
                </td>
                <td>
                    <input type="text" class="form-control" id="class_${loop.index}"
                           name="class_${loop.index}"
                           readonly
                           value="${requestRoom.getApartmentClass()}">
                </td>

                <td>
                    <input type="date" class="form-control" id="arrival_date_${loop.index}"
                           name="arrival_date_${loop.index}"
                           readonly
                           value="${requestRoom.getArrivalDate()}">
                </td>

                <td>
                    <input type="date" class="form-control" id="departure_date_${loop.index}"
                           name="departure_date_${loop.index}"
                           readonly
                           value="${requestRoom.getDepartureDate()}">
                </td>

                <td>
                    <input type="text" class="form-control" id="user_id_${loop.index}"
                           name="user_id_${loop.index}"
                           readonly
                           value="${requestRoom.getUserDto().getId()}">
                </td>

                <td>
                    <input type="text" class="form-control" id="user_nickname_${loop.index}"
                           name="user_nickname_${loop.index}"
                           readonly
                           value="${requestRoom.getUserDto().getLogin()}">
                </td>


                <td>
                    <div class="form-group field-middle_name row mr-2">

                        <c:if test="${requestRoom.getId() !=0}">
                            <button onclick="form.action='show-session';" type="submit" name="search-room"
                                    value="${loop.index}"
                                    class="btn btn-primary ml-2 mr-1">
                                <fmt:message key="local.request.treatment.button.search" bundle="${messages}"/>
                            </button>
                        </c:if>

                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>

<script>
    $(function () {
        window.setTimeout(function () {
            $('#my-alert').alert('close');
        }, 20000);
    });
</script>
</body>
</html>
