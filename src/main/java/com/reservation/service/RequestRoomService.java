package com.reservation.service;

import com.reservation.exception.ServiceException;
import com.reservation.model.converter.dtoConverter.RequestRoomDtoFromEntityConverter;
import com.reservation.model.converter.entityConverter.RequestRoomConverter;
import com.reservation.model.dao.RequestRoomDao;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.entity.RequestRoom;
import com.reservation.model.enums.RequestRoomStatus;

import java.util.List;
import java.util.stream.Collectors;

public class RequestRoomService {


    private final RequestRoomDao requestRoomDao;
    private final RequestRoomConverter requestRoomConverter;
    private final RequestRoomDtoFromEntityConverter requestRoomDtoFromEntityConverter;

    public RequestRoomService(RequestRoomDao requestRoomDao, RequestRoomConverter requestRoomConverter, RequestRoomDtoFromEntityConverter requestRoomDtoFromEntityConverter) {
        this.requestRoomDao = requestRoomDao;
        this.requestRoomConverter = requestRoomConverter;
        this.requestRoomDtoFromEntityConverter = requestRoomDtoFromEntityConverter;
    }

    public void createRequestRoom(RequestRoomDto requestRoomDto) throws ServiceException {
        try {
            RequestRoom requestRoom = requestRoomConverter.convert(requestRoomDto);
            requestRoomDao.insert(requestRoom);
        } catch (Exception e) {
            throw new ServiceException("Create room failed", e);
        }
    }

    public List<RequestRoomDto> receiveNewRequestRooms() throws ServiceException {
        try {
            List<RequestRoom> roomPlaces = requestRoomDao.findByStatus(RequestRoomStatus.NEW.toString());
            return roomPlaces.stream().map(requestRoomDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Show request room failed", e);
        }
    }

}
