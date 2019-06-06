package com.reservation.service;

import com.reservation.exception.ServiceException;
import com.reservation.model.converter.dtoConverter.RoomDtoConverter;
import com.reservation.model.converter.dtoConverter.RoomDtoFromEntityConverter;
import com.reservation.model.converter.entityConverter.RoomConverter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dao.RoomDao;
import com.reservation.model.dto.RoomDto;
import com.reservation.model.entity.Room;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {

    private final RoomDao roomDao;
    private final RoomConverter roomConverter;
    private final RoomDtoConverter roomDtoConverter;
    private final RoomDtoFromEntityConverter roomDtoFromEntityConverter;

    public RoomService(RoomDao roomDao, RoomConverter roomConverter, RoomDtoConverter roomDtoConverter, RoomDtoFromEntityConverter roomDtoFromEntityConverter) {
        this.roomDao = roomDao;
        this.roomConverter = roomConverter;
        this.roomDtoConverter = roomDtoConverter;
        this.roomDtoFromEntityConverter = roomDtoFromEntityConverter;
    }

    public void createRoom(RoomDto roomDto) throws ServiceException {
        try {
            Room room = roomConverter.convert(roomDto);
            roomDao.insert(room);
        } catch (Exception e) {
            throw new ServiceException("Create room failed", e);
        }
    }

    public List<RoomDto> receiveAllRoomsDto() throws ServiceException {
        try {
            return roomDao.findAll().stream().map(roomDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Create room dto failed", e);
        }
    }


    public List<RoomDto> receiveFreeRoomsByParameters(String apartmentClass,
                                                      String arrivalDate,
                                                      String departureDate,
                                                      int placeNumber) throws ServiceException {

        try {
            Date currentArrivalDate = TimeConverter.convertStringToDate(arrivalDate, "yyyy-MM-dd");
            Date currentDepartureDate = TimeConverter.convertStringToDate(departureDate, "yyyy-MM-dd");
            List<Room> rooms = roomDao.receiveFreeRoomsByParameters(currentArrivalDate, currentDepartureDate, placeNumber, apartmentClass);
            return rooms.stream().map(roomDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Received free rooms failed", e);
        }


    }
}
