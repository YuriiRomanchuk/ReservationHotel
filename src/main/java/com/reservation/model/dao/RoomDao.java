package com.reservation.model.dao;

import com.reservation.model.converter.resultSetConverter.RoomResultSetConverter;
import com.reservation.model.entity.Room;

import java.util.List;

public class RoomDao implements GenericDao<Room> {

    private final DataSource dataSource;
    private final RoomResultSetConverter roomResultSetConverter;

    public RoomDao(DataSource dataSource, RoomResultSetConverter roomResultSetConverter) {
        this.dataSource = dataSource;
        this.roomResultSetConverter = roomResultSetConverter;
    }

    @Override
    public void insert(Room entity) {

        final String query = "insert into rooms (room_number, place_number, apartment_class) values(?,?,?";

        dataSource.update(query, ps -> {
            ps.setInt(1, entity.getRoomNumber());
            ps.setInt(2, entity.getPlaceNumber());
            ps.setString(3, entity.getApartmentСlass().toString());
        }, r -> entity.setId(r.getInt(1)));

    }

    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        return dataSource.receiveRecords("select id as room_id, room_number as room_number, place_number as place_number, apartment_class as apartment_class from rooms",
                resultSet -> roomResultSetConverter.convert(resultSet),
                preparedStatement -> {
                });
    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void delete(int id) {

    }
}
