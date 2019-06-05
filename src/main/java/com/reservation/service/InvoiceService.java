package com.reservation.service;

import com.reservation.exception.ServiceException;
import com.reservation.model.converter.dtoConverter.InvoiceDtoConverter;
import com.reservation.model.converter.entityConverter.InvoiceConverter;
import com.reservation.model.dao.InvoiceDao;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.entity.Invoice;

public class InvoiceService {

    private final InvoiceDao invoiceDao;
    private final InvoiceConverter invoiceConverter;
    private final InvoiceDtoConverter invoiceDtoConverter;

    public InvoiceService(InvoiceDao invoiceDao, InvoiceConverter invoiceConverter, InvoiceDtoConverter invoiceDtoConverter) {
        this.invoiceDao = invoiceDao;
        this.invoiceConverter = invoiceConverter;
        this.invoiceDtoConverter = invoiceDtoConverter;
    }

    public void createInvoice(InvoiceDto invoiceDto) throws ServiceException {
        try {
            Invoice invoice = invoiceConverter.convert(invoiceDto);
            invoiceDao.insert(invoice);
        } catch (Exception e) {
            throw new ServiceException("Create invoice failed", e);
        }
    }
}
