package com.reservation.model.dto;

public class RequestRoomDto {

    private int id;
    private int placeNumber;
    private UserDto userDto;
    private String apartmentClass;
    private String arrivalDate;
    private String departureDate;
    private String requestRoomStatus;

    public int getId() {
        return id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public String getApartmentClass() {
        return apartmentClass;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setApartmentClass(String apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getRequestRoomStatus() {
        return requestRoomStatus;
    }

    public void setRequestRoomStatus(String requestRoomStatus) {
        this.requestRoomStatus = requestRoomStatus;
    }
}
