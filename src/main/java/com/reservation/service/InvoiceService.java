package com.reservation.service;

import com.reservation.exception.ServiceException;
import com.reservation.model.converter.dtoConverter.InvoicesDtoFromEntityConverter;
import com.reservation.model.converter.entityConverter.InvoiceConverter;
import com.reservation.model.dao.InvoiceDao;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.dto.UserDto;
import com.reservation.model.entity.Invoice;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService {

    private final InvoiceDao invoiceDao;
    private final InvoiceConverter invoiceConverter;
    private final InvoicesDtoFromEntityConverter invoicesDtoFromEntityConverter;

    public InvoiceService(InvoiceDao invoiceDao, InvoiceConverter invoiceConverter, InvoicesDtoFromEntityConverter invoicesDtoFromEntityConverter) {
        this.invoiceDao = invoiceDao;
        this.invoiceConverter = invoiceConverter;
        this.invoicesDtoFromEntityConverter = invoicesDtoFromEntityConverter;
    }

    public void createInvoice(InvoiceDto invoiceDto) throws ServiceException {
        try {
            Invoice invoice = invoiceConverter.convert(invoiceDto);
            invoiceDao.insert(invoice);
        } catch (Exception e) {
            throw new ServiceException("Create invoice failed", e);
        }
    }

    public List<InvoiceDto> receiveInvoicesById(UserDto userDto) throws ServiceException {
        try {
            List<Invoice> invoices = invoiceDao.findByUserId(userDto.getId());
            return invoices.stream().map(invoicesDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Receive invoice failed", e);
        }
    }

    public void updateInvoiceStatus(int invoiceId, String status) throws ServiceException {
        try {
            invoiceDao.updateStatus(invoiceId, status);
        } catch (Exception e) {
            throw new ServiceException("Update invoice failed", e);
        }
    }
}
