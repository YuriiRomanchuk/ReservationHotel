package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dto.RequestRoomDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RequestRoomDtoConverter implements Converter<HttpServletRequest, RequestRoomDto> {

    private UserDtoConverter userDtoConverter;
    private static final Logger LOGGER = LogManager.getLogger(RoomDtoConverter.class);

    public RequestRoomDtoConverter(UserDtoConverter userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public RequestRoomDto convert(HttpServletRequest request) {
        RequestRoomDto requestRoomDto = new RequestRoomDto();
        requestRoomDto.setPlaceNumber(Integer.valueOf(request.getParameter("placeNumber")));
        requestRoomDto.setApartmentClass(request.getParameter("apartmentClass"));
        requestRoomDto.setUserDto(userDtoConverter.convertFromRequestForUserId(request));
        requestRoomDto.setArrivalDate(TimeConverter.convertStringToDate(request.getParameter("arrivalDate"), "yyyy-mm-dd"));
        requestRoomDto.setDepartureDate(TimeConverter.convertStringToDate(request.getParameter("departureDate"), "yyyy-mm-dd"));
        LOGGER.debug("Room dto is converted from request!");
        return requestRoomDto;
    }
}
