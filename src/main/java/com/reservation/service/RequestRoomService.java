package com.reservation.service;

import com.reservation.exception.ServiceException;
import com.reservation.model.converter.dtoConverter.RequestRoomDtoConverter;
import com.reservation.model.converter.entityConverter.RequestRoomConverter;
import com.reservation.model.dao.RequestRoomDao;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.entity.RequestRoom;
import com.reservation.model.entity.Room;

public class RequestRoomService {


    private final RequestRoomDao requestRoomDao;
    private final RequestRoomConverter requestRoomConverter;
    private final RequestRoomDtoConverter requestRoomDtoConverter;

    public RequestRoomService(RequestRoomDao requestRoomDao, RequestRoomConverter requestRoomConverter, RequestRoomDtoConverter requestRoomDtoConverter) {
        this.requestRoomDao = requestRoomDao;
        this.requestRoomConverter = requestRoomConverter;
        this.requestRoomDtoConverter = requestRoomDtoConverter;
    }

    public void createRequestRoom(RequestRoomDto requestRoomDto) throws ServiceException {
        try {
            RequestRoom requestRoom = requestRoomConverter.convert(requestRoomDto);
            requestRoomDao.insert(requestRoom);
        } catch (Exception e) {
            throw new ServiceException("Create room failed", e);
        }
    }


}
