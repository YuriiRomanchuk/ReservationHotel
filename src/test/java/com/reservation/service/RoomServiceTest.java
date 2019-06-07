package com.reservation.service;

import com.reservation.exception.ServiceException;
import com.reservation.model.converter.dtoConverter.RoomDtoFromEntityConverter;
import com.reservation.model.converter.entityConverter.RoomConverter;
import com.reservation.model.dao.RoomDao;
import com.reservation.model.dto.RoomDto;
import com.reservation.model.entity.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {

    @Mock
    private RoomDao roomDao;

    @Mock
    private RoomConverter roomConverter;

    @Mock
    private RoomDtoFromEntityConverter roomDtoFromEntityConverter;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void createRoom() throws ServiceException {
        RoomDto roomDto = new RoomDto();
        Room room = new Room();
        when(roomConverter.convert(roomDto)).thenReturn(room);
        roomService.createRoom(roomDto);
        verify(roomDao).insert(room);
    }

    @Test
    public void receiveAllRoomsDto() throws ServiceException {
        RoomDto roomDto1 = new RoomDto();
        RoomDto roomDto2 = new RoomDto();
        Room room1 = new Room();
        Room room2 = new Room();

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        when(roomDao.findAll()).thenReturn(rooms);
        when(roomDtoFromEntityConverter.convert(room1)).thenReturn(roomDto1);
        when(roomDtoFromEntityConverter.convert(room2)).thenReturn(roomDto2);

        List<RoomDto> roomsDto = roomService.receiveAllRoomsDto();
        assertEquals(2, roomsDto.size());

        assertEquals(roomDto1, roomsDto.get(0));
        assertEquals(roomDto2, roomsDto.get(1));
    }

    @Test
    public void receiveFreeRoomsByParameters() {

    }
}