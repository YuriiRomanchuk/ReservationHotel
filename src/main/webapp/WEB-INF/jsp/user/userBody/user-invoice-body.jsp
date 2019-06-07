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
        <h1><fmt:message key="local.user.invoice" bundle="${messages}"/></h1>
    </div>
</div>

<div class="w-100 d-none d-md-block"></div>

<form method="post" action="admin-room-selection">
    <table class="table table-sm table-striped">
        <thead>
        <tr>
            <th><fmt:message key="local.user.invoice.table.id" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.place_number" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.class" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.room_number" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.arrival_date" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.departure_date" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.total.price" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.invoice.table.action" bundle="${messages}"/></th>
        </thead>
        <c:forEach var="invoice" items="${invoices}" varStatus="loop">
            <tr>
                <td>
                    <input type="text" class="form-control" id="invoice_id_${loop.index}"
                           name="invoice_id_${loop.index}" size="1"
                           readonly
                           value=" ${invoice.getId()}">
                </td>
                <td>
                    <input type="text" class="form-control" id="place_number_${loop.index}"
                           name="place_number_${loop.index}" size="1"
                           readonly
                           value=" ${invoice.getRoomDto().getPlaceNumber()}">
                </td>
                <td>
                    <input type="text" class="form-control" id="apartment_class_${loop.index}"
                           name="apartment_class_${loop.index}"
                           readonly
                           value="${invoice.getRoomDto().getApartmentClass()}">
                </td>
                <td>
                    <input type="text" class="form-control" id="room_number_${loop.index}"
                           name="room_number_${loop.index}"
                           readonly
                           value="${invoice.getRoomDto().getRoomNumber()}">
                </td>
                <td>
                    <input type="date" class="form-control" id="arrival_date_${loop.index}"
                           name="arrival_date_${loop.index}"
                           readonly
                           value="${invoice.getArrivalDate()}">
                </td>

                <td>
                    <input type="date" class="form-control" id="departure_date_${loop.index}"
                           name="departure_date_${loop.index}"
                           readonly
                           value="${invoice.getDepartureDate()}">
                </td>

                <td>
                    <input type="text" class="form-control" id="price_${loop.index}"
                           name="price_${loop.index}"
                           readonly
                           value="${invoice.getTotalPrice()/100}">
                </td>
                <td>
                    <div class="form-group field-middle_name row mr-2">

                        <c:if test="${invoice.getId() !=0}">
                            <button onclick="form.action='pay-invoice';" type="submit" name="pay-invoice"
                                    value="${loop.index}"
                                    class="btn btn-primary ml-2 mr-1">
                                <fmt:message key="local.user.invoice.button.create.pay" bundle="${messages}"/>
                            </button>

                            <button onclick="form.action='reject-invoice';" type="submit" name="reject-invoice"
                                    value="${loop.index}"
                                    class="btn btn-primary ml-2 mr-1">
                                <fmt:message key="local.user.invoice.button.reject.invoice" bundle="${messages}"/>
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
