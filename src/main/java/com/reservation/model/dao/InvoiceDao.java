package com.reservation.model.dao;

import com.reservation.model.converter.resultSetConverter.InvoiceResultSetConverter;
import com.reservation.model.entity.Invoice;
import com.reservation.model.entity.RequestRoom;

import java.util.List;

public class InvoiceDao  implements GenericDao<Invoice>  {

    private final DataSource dataSource;
    private final InvoiceResultSetConverter invoiceResultSetConverter;

    public InvoiceDao(DataSource dataSource, InvoiceResultSetConverter invoiceResultSetConverter) {
        this.dataSource = dataSource;
        this.invoiceResultSetConverter = invoiceResultSetConverter;
    }

    @Override
    public void insert(Invoice entity) {

        QueryData[] queriesData = new QueryData[2];
        queriesData[0] = (new QueryData("delete from tickets where session_id = ?", ps -> {
            ps.setInt(1, filmSessionId);
        }));
        queriesData[1] = (new QueryData("delete from session where session.id = ?", ps -> {
            ps.setInt(1, filmSessionId);
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
