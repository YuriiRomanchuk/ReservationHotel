package com.reservation.model.dto;

public class InvoiceDto {

    private int id;
    private UserDto userDto;
    private RequestRoomDto requestRoomDto;
    private RoomDto roomDto;
    private String arrivalDate;
    private String departureDate;
    private int totalPrice;
    private String invoiceStatus;

    public int getId() {
        return id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public RequestRoomDto getRequestRoomDto() {
        return requestRoomDto;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setRequestRoomDto(RequestRoomDto requestRoomDto) {
        this.requestRoomDto = requestRoomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
