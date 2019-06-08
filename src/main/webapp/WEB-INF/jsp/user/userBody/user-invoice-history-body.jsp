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

<div class="w-100 justify-content-center">
    <h1><fmt:message key="local.user.invoice.history" bundle="${messages}"/></h1>
</div>

<table id="invoiceTable" class="table table-sm table-striped">
    <thead>
    <tr>
        <th><fmt:message key="local.user.invoice.history.table.id" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.place_number" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.class" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.room_number" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.arrival_date" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.departure_date" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.total.price" bundle="${messages}"/></th>
        <th><fmt:message key="local.user.invoice.history.table.invoice.status" bundle="${messages}"/></th>
    </thead>

    <c:forEach var="invoice" items="${invoices}">
        <tr>
            <td>${invoice.getId()}</td>
            <td>${invoice.getRoomDto().getPlaceNumber()}</td>
            <td>${invoice.getRoomDto().getApartmentClass()}</td>
            <td>${invoice.getRoomDto().getRoomNumber()}</td>
            <td>${invoice.getArrivalDate()}</td>
            <td>${invoice.getDepartureDate()}</td>
            <td>${invoice.getTotalPrice()}</td>
            <td>${invoice.getInvoiceStatus()}</td>
        </tr>
    </c:forEach>
</table>

<script>
    $(document).ready(function () {
        $('#ticketTable').DataTable();
    });

    $(function () {
        window.setTimeout(function () {
            $('#my-alert').alert('close');
        }, 20000);
    });
</script>
</body>
</html>


