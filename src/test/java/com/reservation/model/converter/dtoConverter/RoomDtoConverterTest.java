package com.reservation.model.converter.dtoConverter;

import com.reservation.model.dto.RoomDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomDtoConverterTest {

    @InjectMocks
    private RoomDtoConverter roomDtoConverter;

    @Test
    public void convert() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter("placeNumber")).thenReturn("1");
        when(request.getParameter("apartmentClass")).thenReturn("COMFORT");
        when(request.getParameter("roomNumber")).thenReturn("105");
        when(request.getParameter("price")).thenReturn("123123");

        RoomDto roomDto = receiveRoomDtoTest();
        RoomDto convert = roomDtoConverter.convert(request);

        Assert.assertTrue(new ReflectionEquals(convert).matches(roomDto));

    }

    @Test
    public void convertByRoomId() {
        RoomDto roomDto = new RoomDto();
        int room_id = 1;

        roomDto.setId(room_id);
        RoomDto convert = roomDtoConverter.convertByRoomId(String.valueOf(room_id));
        Assert.assertTrue(new ReflectionEquals(convert).matches(roomDto));
    }

    private RoomDto receiveRoomDtoTest() {
        RoomDto roomDto = new RoomDto();
        roomDto.setPlaceNumber(1);
        roomDto.setApartmentClass("COMFORT");
        roomDto.setRoomNumber(105);
        roomDto.setPrice(123123);

        return roomDto;
    }
}