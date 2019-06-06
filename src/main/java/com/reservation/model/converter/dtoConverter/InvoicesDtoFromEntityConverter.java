package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.entity.Invoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoicesDtoFromEntityConverter implements Converter<Invoice, InvoiceDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public InvoiceDto convert(Invoice invoice) {
        return null;
    }
}
