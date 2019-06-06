package com.reservation.model.dao;

import com.reservation.model.converter.resultSetConverter.InvoiceResultSetConverter;
import com.reservation.model.entity.Invoice;

import java.sql.Timestamp;
import java.util.List;

public class InvoiceDao implements GenericDao<Invoice> {

    private final DataSource dataSource;
    private final InvoiceResultSetConverter invoiceResultSetConverter;

    public InvoiceDao(DataSource dataSource, InvoiceResultSetConverter invoiceResultSetConverter) {
        this.dataSource = dataSource;
        this.invoiceResultSetConverter = invoiceResultSetConverter;
    }

    @Override
    public void insert(Invoice entity) {

        QueryData[] queriesData = new QueryData[2];
        queriesData[0] = (new QueryData("insert into invoices (user_id, request_room_id, room_id, arrival_date, departure_date, total_price, status) values (?,?,?,?,?,?,?)", ps -> {
            ps.setInt(1, entity.getUser().getId());
            ps.setInt(2, entity.getRequestRoom().getId());
            ps.setInt(3, entity.getRoom().getId());
            ps.setTimestamp(4, new Timestamp(entity.getArrivalDate().getTime()));
            ps.setTimestamp(5, new Timestamp(entity.getArrivalDate().getTime()));
            ps.setInt(6, entity.getTotalPrice());
            ps.setString(7, entity.getInvoiceStatus().toString());

        }));
        queriesData[1] = (new QueryData("UPDATE request_rooms SET status = 'APPROVE' WHERE id = ?", ps -> {
            ps.setInt(1, entity.getRequestRoom().getId());
        }));

        dataSource.transactionUpdate(queriesData);
    }

    @Override
    public Invoice findById(int id) {
        return null;
    }

    @Override
    public List<Invoice> findAll() {
        return null;
    }

    @Override
    public void update(Invoice entity) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Invoice> findByUserId(int id) {

        String query = "SELECT temp.id as invoice_id, temp.arrival_date as invoice_arrival_date, temp.departure_date as invoice_departure_date,\n" +
                "       temp.status as invoice_status, temp.total_price as invoice_total_price, temp.room_id as room_id,\n" +
                "       temp.request_room_id as request_room_id, temp.user_id as user_id, users.*, rooms.class as rooms_class,\n" +
                "       rooms.place_number as rooms_place_number, rooms.price as rooms_price, rooms.room_number as rooms_room_number,\n" +
                "       request_rooms.place_number as request_rooms_place_number, request_rooms.class as request_rooms_class,\n" +
                "       request_rooms.user_id as request_rooms_user_id, request_rooms.status as request_rooms_status,\n" +
                "       request_rooms.arrival_date as request_rooms_arrival_date, request_rooms.departure_date as request_rooms_departure_date\n" +
                "FROM\n" +
                "(SELECT * FROM invoices WHERE invoices.user_id = ? and invoices.status = 'NEW') temp LEFT JOIN users on temp.user_id = users.id\n" +
                "                                                                                     LEFT JOIN rooms on temp.room_id = rooms.id\n" +
                "                                                                                     LEFT JOIN request_rooms on temp.request_room_id = request_rooms.id";

        return dataSource.receiveRecords("select *, users.id as user_id  from users",
                resultSet -> invoiceResultSetConverter.convert(resultSet),
                preparedStatement -> {
                    preparedStatement.setInt(1, id);
                });
    }
}
