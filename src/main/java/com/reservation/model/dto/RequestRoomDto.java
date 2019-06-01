package com.reservation.model.dto;

import java.util.Date;

public class RequestRoomDto {

    private int id;
    private int placeNumber;
    private UserDto userDto;
    private String apartmentClass;
    private Date arrivalDate;
    private Date departureDate;

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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Date getDepartureDate() {
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

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
