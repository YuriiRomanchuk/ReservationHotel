package com.reservation.model.dto;

import com.reservation.model.enums.ApartmentСlass;

public class RoomDto {

    private int id;
    private int roomNumber;
    private int placeNumber;
    private ApartmentСlass apartmentСlass;

    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public ApartmentСlass getApartmentСlass() {
        return apartmentСlass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setApartmentСlass(ApartmentСlass apartmentСlass) {
        this.apartmentСlass = apartmentСlass;
    }
}
