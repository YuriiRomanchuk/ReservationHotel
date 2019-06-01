package com.reservation.model.dto;

public class RoomDto {

    private int id;
    private int roomNumber;
    private int placeNumber;
    private String apartmentСlass;

    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public String getApartmentСlass() {
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

    public void setApartmentСlass(String apartmentСlass) {
        this.apartmentСlass = apartmentСlass;
    }
}
