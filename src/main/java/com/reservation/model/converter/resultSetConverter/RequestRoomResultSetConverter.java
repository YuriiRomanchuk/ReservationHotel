package com.reservation.model.converter.resultSetConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.entity.RequestRoom;
import com.reservation.model.enums.ApartmentСlass;
import com.reservation.model.enums.RequestRoomStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRoomResultSetConverter implements Converter<ResultSet, RequestRoom> {

    private static final Logger LOGGER = LogManager.getLogger(RoomResultSetConverter.class);
    private final UserResultSetConverter userResultSetConverter;

    public RequestRoomResultSetConverter(UserResultSetConverter userResultSetConverter) {
        this.userResultSetConverter = userResultSetConverter;
    }

    @Override
    public RequestRoom convert(ResultSet resultSet) throws SQLException {
        RequestRoom requestRoom = new RequestRoom();
        requestRoom.setId(resultSet.getInt("request_room_id"));
        requestRoom.setPlaceNumber(resultSet.getInt("request_rooms_place_number"));
        requestRoom.setApartmentСlass(ApartmentСlass.valueOf(resultSet.getString("request_rooms_class")));
        requestRoom.setRequestRoomStatus(RequestRoomStatus.valueOf(resultSet.getString("request_rooms_status")));
        requestRoom.setArrivalDate(resultSet.getTimestamp("request_rooms_arrival_date"));
        requestRoom.setDepartureDate(resultSet.getTimestamp("request_rooms_departure_date"));
        requestRoom.setUser(userResultSetConverter.convert(resultSet));
        LOGGER.debug("Room result set is converted!");
        return requestRoom;
    }
}
