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

    <div class="w-100 justify-content-center">
        <h1><fmt:message key="local.admin.room.selection" bundle="${messages}"/></h1>
    </div>
</div>

<div class="w-100 d-none d-md-block"></div>

<form method="post" action="admin-room-selection">
    <div class="form-group field-middle_name row">
        <%--<div class="col-sm-6 col-md-6 col-xs-6 no-padding">
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
        </div>--%>

        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="request_room_id"><fmt:message key="local.admin.room.selection.request.room.id"
                                                      bundle="${messages}"/></label>
            <input required type="number" class="form-control" id="request_room_id" name="request_room_id"
                   readonly
                   value=${requestRoomDto.getId()}>
        </div>

        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="request_room_place_number"><fmt:message key="local.admin.room.selection.request.place.number"
                                                                bundle="${messages}"/></label>
            <input required type="number" class="form-control" id="request_room_place_number"
                   name="request_room_place_number"
                   readonly
                   value=${requestRoomDto.getPlaceNumber()}>
        </div>

        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="request_room_apartment_class"><fmt:message
                    key="local.admin.room.selection.request.apartment.class" bundle="${messages}"/></label>
            <input required type="text" class="form-control" id="request_room_apartment_class"
                   name="request_room_apartment_class"
                   readonly
                   value=${requestRoomDto.getApartmentClass()}>
        </div>

        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="request_room_arrival_date"><fmt:message key="local.admin.room.selection.request.arrival.date"
                                                                bundle="${messages}"/></label>
            <input required type="date" class="form-control" id="request_room_arrival_date"
                   name="request_room_arrival_date"
                   readonly
                   value=${requestRoomDto.getArrivalDate()}>
        </div>

        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="request_room_departure_date"><fmt:message
                    key="local.admin.room.selection.request.departure.date" bundle="${messages}"/></label>
            <input required type="date" class="form-control" id="request_room_departure_date"
                   name="request_room_departure_date"
                   readonly
                   value=${requestRoomDto.getDepartureDate()}>
        </div>

        <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
            <label for="request_room_user_id"><fmt:message key="local.admin.room.selection.request.user.id"
                                                           bundle="${messages}"/></label>
            <input required type="number" class="form-control" id="request_room_user_id" name="request_room_user_id"
                   readonly
                   value=${requestRoomDto.getUserDto().getId()}>
        </div>

    </div>
    <%--<div class="help-block row"></div>

    <button type="submit" class="btn btn-primary my-sm-2"><fmt:message key="local.admin.room.selection.button.find"
                                                                       bundle="${messages}"/></button>--%>
<%--/form>--%>

<table class="table table-sm table-striped">
    <thead>
    <tr>
        <th><fmt:message key="local.admin.room.selection.table.id" bundle="${messages}"/></th>
        <th><fmt:message key="local.admin.room.selection.table.place_number" bundle="${messages}"/></th>
        <th><fmt:message key="local.admin.room.selection.table.class" bundle="${messages}"/></th>
        <th><fmt:message key="local.admin.room.selection.table.room_number" bundle="${messages}"/></th>
        <th><fmt:message key="local.admin.room.selection.table.price" bundle="${messages}"/></th>
        <th><fmt:message key="local.admin.room.selection.table.action" bundle="${messages}"/></th>
    </thead>
    <c:forEach var="room" items="${rooms}" varStatus="loop">
        <tr>
            <td>
                <input type="text" class="form-control" id="room_id_${loop.index}"
                       name="room_id_${loop.index}" size="1"
                readonly
                value=" ${room.getId()}">
            </td>
            <td>
                <input type="text" class="form-control" id="place_number_${loop.index}"
                       name="place_number_${loop.index}" size="1"
                       readonly
                       value=" ${room.getPlaceNumber()}">
            </td>
            <td>
                <input type="text" class="form-control" id="room_number_${loop.index}"
                       name="room_number_${loop.index}"
                       readonly
                       value="${room.getRoomNumber()}">
            </td>
            <td>
                <input type="text" class="form-control" id="apartment_class_${loop.index}"
                       name="apartment_class_${loop.index}"
                       readonly
                       value="${room.getApartmentClass()}">
            </td>

            <td>
                <input type="text" class="form-control" id="price_${loop.index}"
                       name="price_${loop.index}"
                       readonly
                       value="${room.getPrice()/100}">
            </td>

            <td>
                <div class="form-group field-middle_name row mr-2">

                    <c:if test="${room.getId() !=0}">
                        <button onclick="form.action='create-invoice';" type="submit" name="create-invoice"
                                value="${loop.index}"
                                class="btn btn-primary ml-2 mr-1">
                            <fmt:message key="local.admin.room.selection.button.create.invoice" bundle="${messages}"/>
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
