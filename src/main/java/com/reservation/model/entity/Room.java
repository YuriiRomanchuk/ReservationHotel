package com.reservation.model.entity;

import com.reservation.model.enums.ApartmentСlass;

public class Room {

    private int id;
    private int roomNumber;
    private int placeNumber;
    private ApartmentСlass apartmentClass;
    private int price;

    public int getId() {
        return id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public ApartmentСlass getApartmentClass() {
        return apartmentClass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setApartmentClass(ApartmentСlass apartmentСlass) {
        this.apartmentClass = apartmentСlass;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
