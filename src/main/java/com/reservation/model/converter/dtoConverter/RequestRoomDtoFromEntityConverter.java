package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.entity.RequestRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestRoomDtoFromEntityConverter implements Converter<RequestRoom, RequestRoomDto> {

    private UserDtoFromEntityConverter userDtoFromEntityConverter;
    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    public RequestRoomDtoFromEntityConverter(UserDtoFromEntityConverter userDtoFromEntityConverter) {
        this.userDtoFromEntityConverter = userDtoFromEntityConverter;
    }

    @Override
    public RequestRoomDto convert(RequestRoom requestRoom) {
        RequestRoomDto requestRoomDto = new RequestRoomDto();
        requestRoomDto.setId(requestRoom.getId());
        requestRoomDto.setUserDto(userDtoFromEntityConverter.convert(requestRoom.getUser()));
        requestRoomDto.setApartmentClass(requestRoom.getApartmentСlass().toString());
        requestRoomDto.setPlaceNumber(requestRoom.getPlaceNumber());
        requestRoomDto.setRequestRoomStatus(requestRoom.getRequestRoomStatus().toString());
        requestRoomDto.setArrivalDate(TimeConverter.changeDataToStringFormat(requestRoom.getArrivalDate(), "yyyy-MM-dd"));
        requestRoomDto.setDepartureDate(TimeConverter.changeDataToStringFormat(requestRoom.getDepartureDate(), "yyyy-MM-dd"));
        LOGGER.debug("Request room dto is converted from entity!");
        return requestRoomDto;
    }
}
