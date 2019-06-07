package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.dto.RoomDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RoomDtoConverter implements Converter<HttpServletRequest, RoomDto> {

    private static final Logger LOGGER = LogManager.getLogger(RoomDtoConverter.class);

    @Override
    public RoomDto convert(HttpServletRequest request) {
        RoomDto roomDto = new RoomDto();
        roomDto.setPlaceNumber(Integer.valueOf(request.getParameter("placeNumber")));
        roomDto.setApartmentClass(request.getParameter("apartmentClass"));
        roomDto.setRoomNumber(Integer.valueOf(request.getParameter("roomNumber")));
        roomDto.setPrice(Integer.valueOf(request.getParameter("price")));
        LOGGER.debug("Room dto is converted from request!");
        return roomDto;
    }

    //TODO: refactoring to another class
    public RoomDto convertByRoomId(String room_id) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room_id == null ? 0 : Integer.valueOf(room_id));
        LOGGER.debug("Room dto is converted by room id!");
        return roomDto;
    }
}