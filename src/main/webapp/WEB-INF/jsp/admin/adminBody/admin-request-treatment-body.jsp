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
        <h1><fmt:message key="local.admin.session" bundle="${messages}"/></h1>
    </div>
</div>

<div class="w-100 d-none d-md-block"></div>

<form method="post" action="admin-session">
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
            <label for="date_filter"><fmt:message key="local.admin.session.date" bundle="${messages}"/></label>
            <input required type="date" class="form-control" id="date_filter" name="date_filter"
                   placeholder="Enter session date"
                   value=${filterDate}>
        </div>
    </div>
    <div class="help-block row"></div>

    <button type="submit" class="btn btn-primary my-sm-2"><fmt:message key="local.admin.session.button.find"
                                                                       bundle="${messages}"/></button>
    <%--</form>--%>

    <table class="table table-sm table-striped">
        <thead>
        <tr>
            <th><fmt:message key="local.admin.session.table.id" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.session.table.date" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.session.table.room" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.session.table.film" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.session.table.action" bundle="${messages}"/></th>
        </thead>
        <c:forEach var="filmSessionDto" items="${filmsSessionDto}" varStatus="loop">
            <tr>
                <td>
                    <input type="text" class="form-control" id="session_id_${loop.index}"
                           name="session_id_${loop.index}" size="1"
                           readonly
                           value=" ${filmSessionDto.getId()}">
                </td>
                <td>
                    <input type="text" class="form-control" id="session_date_${loop.index}"
                           name="session_date_${loop.index}"
                           placeholder="Enter film's name" readonly
                           value="${filmSessionDto.getDate()}">
                </td>
                <td>
                    <select
                    <c:choose>
                        <c:when test="${filmSessionDto.getId() !=0}">
                            <select class="custom-select mr-sm-2" id="session_film_${loop.index}"
                                    name="session_film_${loop.index}">
                                <c:if test="${filmSessionDto.getFilmDto() !=null}">
                                    <option selected="selected"
                                            value=${filmSessionDto.getRoomDto().getId()}>${filmSessionDto.getRoomDto().getName()}
                                    </option>
                                </c:if>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select
                                    class="custom-select mr-sm-2" id="session_room_${loop.index}"
                                    name="session_room_${loop.index}">
                                <option selected><fmt:message key="local.admin.session.film.choose"
                                                              bundle="${messages}"/></option>
                                <c:forEach var="room" items="${roomsDto}">
                                    <option selected="selected" value=${room.getId()}>
                                        <c:choose>
                                            <c:when test="${sessionScope.lang = 'uk'}">
                                                ${room.getName()}
                                            </c:when>
                                            <c:otherwise>
                                                ${room.getNameEnglish()}
                                            </c:otherwise>
                                        </c:choose>
                                    </option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>

                    </select>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${filmSessionDto.getId() !=0}">
                            <select class="custom-select mr-sm-2" id="session_film_${loop.index}"
                                    name="session_film_${loop.index}">
                                <c:if test="${filmSessionDto.getFilmDto() !=null}">
                                    <option selected="selected"
                                            value=${filmSessionDto.getFilmDto().getId()}>

                                        <c:choose>
                                            <c:when test="${sessionScope.lang = 'uk'}">
                                                ${filmSessionDto.getFilmDto().getName()}
                                            </c:when>
                                            <c:otherwise>
                                                ${filmSessionDto.getFilmDto().getNameEnglish()}
                                            </c:otherwise>
                                        </c:choose>
                                    </option>
                                </c:if>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select
                                    class="custom-select mr-sm-2" id="session_film_${loop.index}"
                                    name="session_film_${loop.index}">
                                <option selected><fmt:message key="local.admin.session.film.choose"
                                                              bundle="${messages}"/></option>
                                <c:forEach var="film" items="${filmsDto}">
                                    <option value=${film.getId()}>
                                        <c:choose>
                                            <c:when test="${sessionScope.lang = 'uk'}">
                                                ${film.getName()}
                                            </c:when>
                                            <c:otherwise>
                                                ${film.getNameEnglish()}
                                            </c:otherwise>
                                        </c:choose>

                                    </option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>

                </td>

                <td>
                    <div class="form-group field-middle_name row mr-2">

                        <c:if test="${filmSessionDto.getId() !=0}">
                            <button onclick="form.action='show-session';" type="submit" name="show-session"
                                    value="${loop.index}"
                                    class="btn btn-primary ml-2 mr-1">
                                <fmt:message key="local.admin.session.button.show" bundle="${messages}"/>
                            </button>

                            <c:if test="${!isLastDay}">
                                <button onclick="form.action='delete-session';" type="submit" name="delete-session"
                                        value="${loop.index}"
                                        class="btn btn-danger">
                                    <fmt:message key="local.admin.session.button.delete" bundle="${messages}"/>
                                </button>
                            </c:if>
                        </c:if>

                        <c:if test="${filmSessionDto.getId() <=0 and !isLastDay}">
                            <button onclick="form.action='add-session';" type="submit" name="add-session"
                                    value="${loop.index}"
                                    class="btn btn-success ml-2">
                                <fmt:message key="local.admin.session.button.add" bundle="${messages}"/>
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
