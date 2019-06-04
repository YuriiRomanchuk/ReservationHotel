package com.reservation.model.dto;

public class RoomDto {

    private int id;
    private int roomNumber;
    private int placeNumber;
    private int price;
    private String apartmentClass;

    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public String getApartmentClass() {
        return apartmentClass;
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

    public void setApartmentClass(String apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
