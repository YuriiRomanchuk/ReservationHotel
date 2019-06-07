<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <title>Title</title>
</head>

<body>

<c:set var='error' value="${Error}"/>
<div class="col w-100">

    <%-- <c:if test="${error !=null}">
         <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100" role="alert">
                 ${error}
             <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                 <span aria-hidden="true">&times;</span>
             </button>
         </div>
         <div class="w-100 d-none d-md-block"></div>
     </c:if>--%>

    <div class="w-100 justify-content-center">
        <h1>History</h1>
    </div>
</div>

<table id="ticketTable" class="table table-sm table-striped">
    <thead>
    <tr>
        <th>Id</th>
        <th>Place number</th>
        <th>Apartment class</th>
        <th>Arrival date</th>
        <th>Departure date</th>
        <th>Status</th>
    </thead>

    <c:forEach var="request" items="${requestsRoom}">
        <tr>
            <td>${request.getId()}</td>
            <td>${request.getPlaceNumber()}</td>
            <td>${request.getApartmentClass()}</td>
            <td>${request.getArrivalDate()}</td>
            <td>${request.getDepartureDate()}</td>
            <td>${request.getRequestRoomStatus()}</td>
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


