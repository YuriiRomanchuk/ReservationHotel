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
        queriesData[0] = (new QueryData("insert into invoice (user_id, request_room_id, room_id, arrival_date, departure_date, total_price, status) values(?,?,?,?,?,?,?)", ps -> {
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
}
