package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
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
        requestRoomDto.setRequestRoomStatus(request.getParameter("status"));
        requestRoomDto.setUserDto(userDtoConverter.convertFromRequestForUserId(request));
        requestRoomDto.setArrivalDate(request.getParameter("arrivalDate"));
        requestRoomDto.setDepartureDate(request.getParameter("departureDate"));
        LOGGER.debug("Room dto is converted from request!");
        return requestRoomDto;
    }

    public int receiveRequestRoomId(HttpServletRequest request) {
        String numberOfLine = request.getParameter("search-room");
        return Integer.valueOf(request.getParameter("request_id_" + numberOfLine).trim());
    }

    public int receiveRequestRoomIdFromURI(HttpServletRequest request) {
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/main", "");
        String[] splitURI = requestURI.split("/");
        LOGGER.debug("Request room id from command line received from request");
        return Integer.valueOf(splitURI[splitURI.length - 1]);
    }

}
