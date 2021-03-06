package com.reservation.model.dao;

import com.reservation.model.converter.resultSetConverter.RequestRoomResultSetConverter;
import com.reservation.model.entity.RequestRoom;

import java.sql.Timestamp;
import java.util.List;

public class RequestRoomDao implements GenericDao<RequestRoom> {

    private final DataSource dataSource;
    private final RequestRoomResultSetConverter roomResultSetConverter;


    public RequestRoomDao(DataSource dataSource, RequestRoomResultSetConverter roomResultSetConverter) {
        this.dataSource = dataSource;
        this.roomResultSetConverter = roomResultSetConverter;
    }

    @Override
    public void insert(RequestRoom entity) {

        final String query = "insert into request_rooms (place_number, arrival_date, departure_date, class, status, user_id) values(?,?,?,?,?,?)";

        dataSource.update(query, ps -> {
            ps.setInt(1, entity.getPlaceNumber());
            ps.setTimestamp(2, new Timestamp(entity.getArrivalDate().getTime()));
            ps.setTimestamp(3, new Timestamp(entity.getDepartureDate().getTime()));
            ps.setString(4, entity.getApartmentСlass().toString());
            ps.setString(5, entity.getRequestRoomStatus().toString());
            ps.setInt(6, entity.getUser().getId());
        }, r -> entity.setId(r.getInt(1)));

    }

    @Override
    public RequestRoom findById(int id) {

        return dataSource.receiveFirstRecord("SELECT  temp.place_number as request_rooms_place_number, temp.class as request_rooms_class,\n" +
                        "temp.user_id as user_id, temp.status as request_rooms_status,\n" +
                        "temp.arrival_date as request_rooms_arrival_date, temp.departure_date as request_rooms_departure_date, temp.id as request_room_id, users.*\n" +
                        "  FROM\n" +
                        "(SELECT *, id as request_id from request_rooms where id = ?) temp LEFT JOIN users ON temp.user_id = users.id",
                resultSet -> roomResultSetConverter.convert(resultSet),
                preparedStatement ->
                {
                    preparedStatement.setInt(1, id);
                }).get();
    }

    @Override
    public List<RequestRoom> findAll() {
        return dataSource.receiveRecords("SELECT  temp.place_number as request_rooms_place_number, temp.class as request_rooms_class,\n" +
                        "temp.user_id as user_id, temp.status as request_rooms_status,\n" +
                        "temp.arrival_date as request_rooms_arrival_date, temp.departure_date as request_rooms_departure_date, temp.id as request_room_id, users.*\n" +
                        " FROM\n" +
                        "(SELECT *, id as request_id from request_rooms) temp LEFT JOIN users ON temp.user_id = users.id",
                resultSet -> roomResultSetConverter.convert(resultSet),
                preparedStatement -> {
                });
    }

    @Override
    public void update(RequestRoom entity) {

    }

    @Override
    public void delete(int id) {

    }

    public List<RequestRoom> findByStatus(String status) {
        return dataSource.receiveRecords("SELECT  temp.place_number as request_rooms_place_number, temp.class as request_rooms_class,\n" +
                        " temp.user_id as user_id, temp.status as request_rooms_status,\n" +
                        " temp.arrival_date as request_rooms_arrival_date, temp.departure_date as request_rooms_departure_date, temp.id as request_room_id, users.*\n" +
                        " FROM\n" +
                        "(SELECT *, id as request_id from request_rooms where status = ?) temp LEFT JOIN users ON temp.user_id = users.id",
                resultSet -> roomResultSetConverter.convert(resultSet),
                preparedStatement ->
                {
                    preparedStatement.setString(1, status);
                });
    }

    public void updateRequestStatus(int requestRoomId, String status) {

        QueryData[] queriesData = new QueryData[1];

        queriesData[0] = (new QueryData("UPDATE request_rooms SET status = ? WHERE id = ?", ps -> {
            ps.setString(1, status);
            ps.setInt(2, requestRoomId);
        }));

        dataSource.transactionUpdate(queriesData);
    }

    public List<RequestRoom> findByUserId(int userId) {

        return dataSource.receiveRecords("SELECT  temp.place_number as request_rooms_place_number, temp.class as request_rooms_class,\n" +
                        " temp.user_id as user_id, temp.status as request_rooms_status,\n" +
                        " temp.arrival_date as request_rooms_arrival_date, temp.departure_date as request_rooms_departure_date, temp.id as request_room_id, users.*\n" +
                        " FROM\n" +
                        "(SELECT *, id as request_id from request_rooms where user_id = ?) temp LEFT JOIN users ON temp.user_id = users.id",
                resultSet -> roomResultSetConverter.convert(resultSet),
                preparedStatement ->
                {
                    preparedStatement.setInt(1, userId);
                });
    }
}
