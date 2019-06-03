package com.reservation.model.entity;

import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.dto.RoomDto;

import java.util.Date;

public class Invoice {

    private int id;
    private User user;
    private RequestRoomDto requestRoomDto;
    private RoomDto roomDto;
    private Date arrivalDate;
    private Date departureDate;
    private int totalPrice;

}
