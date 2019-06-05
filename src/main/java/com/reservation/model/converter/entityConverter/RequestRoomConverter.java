package com.reservation.model.converter.entityConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.entity.RequestRoom;
import com.reservation.model.enums.ApartmentСlass;
import com.reservation.model.enums.RequestRoomStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestRoomConverter implements Converter<RequestRoomDto, RequestRoom> {

    private static final Logger LOGGER = LogManager.getLogger(RoomConverter.class);
    private UserConverter userConverter;

    public RequestRoomConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public RequestRoom convert(RequestRoomDto requestRoomDto) {
        RequestRoom requestRoom = new RequestRoom();
        requestRoom.setId(requestRoomDto.getId());
        requestRoom.setUser(userConverter.convert(requestRoomDto.getUserDto()));
        requestRoom.setApartmentСlass(ApartmentСlass.valueOf(requestRoomDto.getApartmentClass()));
        requestRoom.setRequestRoomStatus(RequestRoomStatus.valueOf(requestRoomDto.getRequestRoomStatus()));
        requestRoom.setArrivalDate(TimeConverter.receiveBeginOfDay(TimeConverter.convertStringToDate(requestRoomDto.getArrivalDate(), "yyyy-MM-dd")));
        requestRoom.setDepartureDate(TimeConverter.receiveBeginOfDay(TimeConverter.convertStringToDate(requestRoomDto.getDepartureDate(), "yyyy-MM-dd")));
        requestRoom.setPlaceNumber(requestRoomDto.getPlaceNumber());
        LOGGER.debug("Request room is converted from room dto!");
        return requestRoom;
    }
}
