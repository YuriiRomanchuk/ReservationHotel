package com.reservation.model.entity;

import com.reservation.model.enums.ApartmentСlass;

import java.util.Date;

public class RequestRoom {

    private int id;
    private int placeNumber;
    private User user;
    private ApartmentСlass apartmentСlass;
    private Date arrivalDate;
    private Date departureDate;

    public int getId() {
        return id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public User getUser() {
        return user;
    }

    public ApartmentСlass getApartmentСlass() {
        return apartmentСlass;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setApartmentСlass(ApartmentСlass apartmentСlass) {
        this.apartmentСlass = apartmentСlass;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
