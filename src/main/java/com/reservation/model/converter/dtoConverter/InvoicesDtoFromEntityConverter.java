package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.entity.Invoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoicesDtoFromEntityConverter implements Converter<Invoice, InvoiceDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);
    private UserDtoFromEntityConverter userDtoFromEntityConverter;
    private RequestRoomDtoFromEntityConverter requestRoomDtoFromEntityConverter;
    private RoomDtoFromEntityConverter roomDtoFromEntityConverter;


    public InvoicesDtoFromEntityConverter(UserDtoFromEntityConverter userDtoFromEntityConverter,
                                          RequestRoomDtoFromEntityConverter requestRoomDtoFromEntityConverter,
                                          RoomDtoFromEntityConverter roomDtoFromEntityConverter) {
        this.userDtoFromEntityConverter = userDtoFromEntityConverter;
        this.requestRoomDtoFromEntityConverter = requestRoomDtoFromEntityConverter;
        this.roomDtoFromEntityConverter = roomDtoFromEntityConverter;
    }

    @Override
    public InvoiceDto convert(Invoice invoice) {

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setInvoiceStatus(invoice.getInvoiceStatus().toString());
        invoiceDto.setTotalPrice(invoice.getTotalPrice());
        invoiceDto.setArrivalDate(TimeConverter.changeDataToStringFormat(invoice.getArrivalDate(), "yyyy-MM-dd"));
        invoiceDto.setDepartureDate(TimeConverter.changeDataToStringFormat(invoice.getDepartureDate(), "yyyy-MM-dd"));
        invoiceDto.setRequestRoomDto(requestRoomDtoFromEntityConverter.convert(invoice.getRequestRoom()));
        invoiceDto.setRoomDto(roomDtoFromEntityConverter.convert(invoice.getRoom()));
        invoiceDto.setUserDto(userDtoFromEntityConverter.convert(invoice.getUser()));
        LOGGER.debug("Invoice dto is converted from entity!");
        return invoiceDto;
    }
}
