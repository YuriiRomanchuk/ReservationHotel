package com.reservation.model.converter.entityConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.entity.Invoice;
import com.reservation.model.enums.InvoiceStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoiceConverter implements Converter<InvoiceDto, Invoice> {

    private static final Logger LOGGER = LogManager.getLogger(RoomConverter.class);
    private UserConverter userConverter;
    private RoomConverter roomConverter;
    private RequestRoomConverter requestRoomConverter;

    public InvoiceConverter(UserConverter userConverter, RoomConverter roomConverter, RequestRoomConverter requestRoomConverter) {
        this.userConverter = userConverter;
        this.roomConverter = roomConverter;
        this.requestRoomConverter = requestRoomConverter;
    }

    @Override
    public Invoice convert(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceStatus(InvoiceStatus.valueOf(invoiceDto.getInvoiceStatus()));
        invoice.setUser(userConverter.convert(invoiceDto.getUserDto()));
        invoice.setRequestRoom(requestRoomConverter.convert(invoiceDto.getRequestRoomDto()));
        invoice.setRoom(roomConverter.convert(invoiceDto.getRoomDto()));
        invoice.setArrivalDate(TimeConverter.receiveBeginOfDay(TimeConverter.convertStringToDate(invoiceDto.getArrivalDate(), "yyyy-MM-dd")));
        invoice.setDepartureDate(TimeConverter.receiveBeginOfDay(TimeConverter.convertStringToDate(invoiceDto.getDepartureDate(), "yyyy-MM-dd")));
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        LOGGER.debug("Invoice is converted from invoice dto!");
        return invoice;
    }
}
