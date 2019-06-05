package com.reservation.model.converter.resultSetConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.entity.Invoice;

import java.sql.ResultSet;

public class InvoiceResultSetConverter implements Converter<ResultSet, Invoice> {

    @Override
    public Invoice convert(ResultSet resultSet) {
        return null;
    }
}
