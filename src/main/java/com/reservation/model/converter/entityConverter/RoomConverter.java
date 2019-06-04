package com.reservation.model.converter.entityConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.dto.RoomDto;
import com.reservation.model.entity.Room;
import com.reservation.model.enums.ApartmentСlass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomConverter implements Converter<RoomDto, Room> {

    private static final Logger LOGGER = LogManager.getLogger(RoomConverter.class);

    @Override
    public Room convert(RoomDto roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setApartmentClass(ApartmentСlass.valueOf(roomDto.getApartmentClass()));
        room.setPlaceNumber(roomDto.getPlaceNumber());
        room.setPrice(roomDto.getPrice());
        LOGGER.debug("Room is converted from room dto!");
        return room;
    }

}
