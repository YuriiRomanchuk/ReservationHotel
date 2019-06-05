package com.reservation.model.entity;

import com.reservation.model.enums.InvoiceStatus;

import java.util.Date;

public class Invoice {

    private int id;
    private User user;
    private RequestRoom requestRoom;
    private Room room;
    private Date arrivalDate;
    private Date departureDate;
    private int totalPrice;
    private InvoiceStatus invoiceStatus;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public RequestRoom getRequestRoom() {
        return requestRoom;
    }

    public Room getRoom() {
        return room;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRequestRoom(RequestRoom requestRoom) {
        this.requestRoom = requestRoom;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
