package com.reservation.model.dao;

import com.reservation.model.converter.resultSetConverter.RoomResultSetConverter;
import com.reservation.model.entity.Room;
import com.reservation.model.enums.InvoiceStatus;

import java.sql.Timestamp;
import java.util.Date;
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

        final String query = "insert into rooms (room_number, place_number, class, price) values(?,?,?,?)";

        dataSource.update(query, ps -> {
            ps.setInt(1, entity.getRoomNumber());
            ps.setInt(2, entity.getPlaceNumber());
            ps.setString(3, entity.getApartmentClass().toString());
            ps.setInt(4, entity.getPrice());
        }, r -> entity.setId(r.getInt(1)));

    }

    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        return dataSource.receiveRecords("select id as room_id, room_number as room_number, place_number as place_number," +
                        " apartment_class as apartment_class, price from rooms",
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

    public List<Room> receiveFreeRoomsByParameters(Date currentArrivalDate, Date currentDepartureDate, int placeNumber, String apartmentClass) {
        return dataSource.receiveRecords(
                " SELECT rooms.*, invoice_id, rooms.id as rooms_room_id, rooms.class as rooms_class, " +
                        "      rooms.place_number as rooms_place_number, rooms.price as rooms_price, rooms.room_number as rooms_room_number, rooms.id as room_id \n" +
                        "      FROM\n" +
                        "        (SELECT MAX(invoices.id) as invoice_id, invoices.room_id FROM\n" +
                        "          (SELECT * FROM rooms  WHERE class = ? and place_number >= ?) temp LEFT JOIN invoices on temp.id = invoices.room_id\n" +
                        "         WHERE invoices.status <> ? and ((invoices.arrival_date >= ? and invoices.departure_date <= ?)" +
                        "or (invoices.arrival_date <= ? and invoices.departure_date >= ?) " +
                        "or (invoices.arrival_date <= ? and invoices.departure_date >= ?)" +
                        "or (invoices.arrival_date <= ? and invoices.departure_date >= ?)) GROUP BY invoices.room_id) temp2" +
                        " RIGHT JOIN rooms ON rooms.id = temp2.room_id WHERE temp2.invoice_id ISNULL and rooms.class = ? and rooms.place_number >= ?\n" +
                        "\n",
                resultSet -> roomResultSetConverter.convert(resultSet),
                ps -> {
                    ps.setString(1, apartmentClass);
                    ps.setInt(2, placeNumber);
                    ps.setString(3, InvoiceStatus.REJECT.toString());
                    ps.setTimestamp(4, new Timestamp(currentArrivalDate.getTime()));
                    ps.setTimestamp(5, new Timestamp(currentDepartureDate.getTime()));
                    ps.setTimestamp(6, new Timestamp(currentArrivalDate.getTime()));
                    ps.setTimestamp(7, new Timestamp(currentArrivalDate.getTime()));
                    ps.setTimestamp(8, new Timestamp(currentDepartureDate.getTime()));
                    ps.setTimestamp(9, new Timestamp(currentDepartureDate.getTime()));
                    ps.setTimestamp(10, new Timestamp(currentArrivalDate.getTime()));
                    ps.setTimestamp(11, new Timestamp(currentDepartureDate.getTime()));
                    ps.setString(12, apartmentClass);
                    ps.setInt(13, placeNumber);

                });
    }
}
