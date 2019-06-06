package com.reservation.controller;

import com.reservation.exception.ServiceException;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.dto.UserDto;
import com.reservation.model.enums.InvoiceStatus;
import com.reservation.service.InvoiceService;
import com.reservation.service.RequestRoomService;
import com.reservation.service.RoomService;
import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;
import com.reservation.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoiceController {

    private static final Logger LOGGER = LogManager.getLogger(RoomController.class);
    private final InvoiceService invoiceService;
    private final RequestRoomService requestRoomService;
    private final RoomService roomService;

    public InvoiceController(InvoiceService invoiceService, RequestRoomService requestRoomService, RoomService roomService) {
        this.invoiceService = invoiceService;
        this.requestRoomService = requestRoomService;
        this.roomService = roomService;
    }

    public View createInvoice(InvoiceDto invoiceDto) {
        View view;
        try {
            invoiceService.createInvoice(invoiceDto);
            view = receiveViewModel("admin-request-treatment", "Invoice created!");
            view.addParameter("requestRooms", requestRoomService.receiveNewRequestRooms());
        } catch (ServiceException e) {
            view = prepareInvoiceExceptionRedirectPage(invoiceDto, e);
        }
        return new RedirectViewModel(view);
    }

    private View prepareInvoiceExceptionRedirectPage(InvoiceDto invoiceDto, ServiceException exception) {
        View view;
        try {
            view = receiveViewModel("admin-room-selection/" + invoiceDto.getRequestRoomDto().getId(), exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage());
            RequestRoomDto requestRoomDto = requestRoomService.receiveRequestRoomById(invoiceDto.getRequestRoomDto().getId());
            view.addParameter("requestRoomDto", requestRoomDto);
            view.addParameter("rooms", roomService.receiveFreeRoomsByParameters(requestRoomDto.getApartmentClass(), requestRoomDto.getArrivalDate(),
                    requestRoomDto.getDepartureDate(), requestRoomDto.getPlaceNumber()));
        } catch (ServiceException e) {
            return receiveViewModel("admin-personal-area", exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        LOGGER.debug(error);
        view.addParameter("Error", error);
        return view;
    }

    public View showUserInvoicePage(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-invoice.jsp");
            view.addParameter("invoices", invoiceService.receiveInvoicesById(userDto));
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View payInvoice(int invoiceId) {
        View view;
        try {
            invoiceService.updateInvoiceStatus(invoiceId, InvoiceStatus.PAID.toString());
            view = new ViewModel("user-invoices");
           /* return view;*/
        } catch (ServiceException e) {
            view = receiveViewModel("user-invoices", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }
}

