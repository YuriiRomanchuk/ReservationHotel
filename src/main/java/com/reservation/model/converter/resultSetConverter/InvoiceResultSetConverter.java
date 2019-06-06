package com.reservation.model.converter.resultSetConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.entity.Invoice;
import com.reservation.model.enums.InvoiceStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceResultSetConverter implements Converter<ResultSet, Invoice> {

    private static final Logger LOGGER = LogManager.getLogger(RoomResultSetConverter.class);
    private final UserResultSetConverter userResultSetConverter;
    private final RoomResultSetConverter roomResultSetConverter;
    private final RequestRoomResultSetConverter requestRoomResultSetConverter;


    public InvoiceResultSetConverter(UserResultSetConverter userResultSetConverter,
                                     RoomResultSetConverter roomResultSetConverter,
                                     RequestRoomResultSetConverter requestRoomResultSetConverter) {
        this.userResultSetConverter = userResultSetConverter;
        this.roomResultSetConverter = roomResultSetConverter;
        this.requestRoomResultSetConverter = requestRoomResultSetConverter;
    }

    @Override
    public Invoice convert(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getInt("invoice_id"));
        invoice.setTotalPrice(resultSet.getInt("invoice_total_price"));
        invoice.setArrivalDate(resultSet.getTimestamp("invoice_arrival_date"));
        invoice.setDepartureDate(resultSet.getTimestamp("invoice_departure_date"));
        invoice.setInvoiceStatus(InvoiceStatus.valueOf(resultSet.getString("invoice_status")));
        invoice.setUser(userResultSetConverter.convert(resultSet));
        invoice.setRequestRoom(requestRoomResultSetConverter.convert(resultSet));
        invoice.setRoom(roomResultSetConverter.convert(resultSet));
        LOGGER.debug("Invoice result set is converted!");
        return invoice;
    }
}
