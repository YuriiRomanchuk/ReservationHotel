package com.reservation.service;

import com.reservation.model.converter.dtoConverter.InvoiceDtoConverter;
import com.reservation.model.converter.entityConverter.InvoiceConverter;
import com.reservation.model.dao.InvoiceDao;
import com.reservation.model.dto.InvoiceDto;

public class InvoiceService {

    private final InvoiceDao invoiceDao;
    private final InvoiceConverter roomConverter;
    private final InvoiceDtoConverter roomDtoConverter;

    public InvoiceService(InvoiceDao invoiceDao, InvoiceConverter roomConverter, InvoiceDtoConverter roomDtoConverter) {
        this.invoiceDao = invoiceDao;
        this.roomConverter = roomConverter;
        this.roomDtoConverter = roomDtoConverter;
    }

    public void createInvoice(InvoiceDto invoiceDto) {

    }
}
