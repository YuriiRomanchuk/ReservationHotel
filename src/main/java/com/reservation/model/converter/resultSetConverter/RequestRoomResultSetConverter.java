package com.reservation.model.converter.resultSetConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.entity.RequestRoom;
import com.reservation.model.enums.ApartmentСlass;
import com.reservation.model.enums.RequestRoomStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public class RequestRoomResultSetConverter implements Converter<ResultSet, RequestRoom> {

    private final UserResultSetConverter userResultSetConverter;
    private static final Logger LOGGER = LogManager.getLogger(RoomResultSetConverter.class);

    public RequestRoomResultSetConverter(UserResultSetConverter userResultSetConverter) {
        this.userResultSetConverter = userResultSetConverter;
    }

    @Override
    public RequestRoom convert(ResultSet resultSet) throws Exception {
        RequestRoom requestRoom = new RequestRoom();
        requestRoom.setPlaceNumber(resultSet.getInt("place_number"));
        requestRoom.setApartmentСlass(ApartmentСlass.valueOf(resultSet.getString("class")));
        requestRoom.setRequestRoomStatus(RequestRoomStatus.valueOf(resultSet.getString("status")));
        requestRoom.setArrivalDate(resultSet.getTimestamp("arrival_date"));
        requestRoom.setDepartureDate(resultSet.getTimestamp("departure_date"));
        requestRoom.setUser(userResultSetConverter.convert(resultSet));
        LOGGER.debug("Room result set is converted!");
        return requestRoom;
    }
}
