package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.dto.RoomDto;
import com.reservation.model.entity.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomDtoFromEntityConverter implements Converter<Room, RoomDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public RoomDto convert(Room room)  {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setApartmentClass(room.getApartmentClass().toString());
        roomDto.setPlaceNumber(room.getPlaceNumber());
        roomDto.setPrice(room.getPrice());
        LOGGER.debug("Room dto is converted by entity!");
        return roomDto;
    }
}
