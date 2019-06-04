package com.reservation.model.converter.resultSetConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.entity.Room;
import com.reservation.model.enums.ApartmentСlass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomResultSetConverter implements Converter<ResultSet, Room> {

    private static final Logger LOGGER = LogManager.getLogger(RoomResultSetConverter.class);

    @Override
    public Room convert(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setPlaceNumber(resultSet.getInt("place_number"));
        room.setApartmentClass(ApartmentСlass.valueOf(resultSet.getString("class")));
        room.setRoomNumber(resultSet.getInt("room_number"));
        room.setId(resultSet.getInt("room_id"));
        room.setPrice(resultSet.getInt("price"));
        LOGGER.debug("Room result set is converted!");
        return room;
    }
}
