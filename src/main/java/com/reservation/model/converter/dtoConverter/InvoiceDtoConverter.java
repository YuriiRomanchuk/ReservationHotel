package com.reservation.model.converter.dtoConverter;

import com.reservation.model.converter.Converter;
import com.reservation.model.converter.utility.TimeConverter;
import com.reservation.model.dto.InvoiceDto;
import com.reservation.model.enums.InvoiceStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class InvoiceDtoConverter implements Converter<HttpServletRequest, InvoiceDto> {

    private static final Logger LOGGER = LogManager.getLogger(RoomDtoConverter.class);
    private UserDtoConverter userDtoConverter;
    private RoomDtoConverter roomDtoConverter;
    private RequestRoomDtoConverter requestRoomDtoConverter;

    public InvoiceDtoConverter(UserDtoConverter userDtoConverter, RoomDtoConverter roomDtoConverter, RequestRoomDtoConverter requestRoomDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.roomDtoConverter = roomDtoConverter;
        this.requestRoomDtoConverter = requestRoomDtoConverter;
    }

    @Override
    public InvoiceDto convert(HttpServletRequest request) {
        String numberOfLine = request.getParameter("create-invoice");

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setUserDto(userDtoConverter.convertByUserId(Integer.valueOf(request.getParameter("request_room_user_id"))));
        invoiceDto.setRoomDto(roomDtoConverter.convertByRoomId(request.getParameter("room_id_" + numberOfLine).trim()));
        invoiceDto.setRequestRoomDto(requestRoomDtoConverter.convertByRequestId(Integer.valueOf(request.getParameter("request_room_id"))));
        invoiceDto.setArrivalDate(request.getParameter("request_room_arrival_date"));
        invoiceDto.setDepartureDate(request.getParameter("request_room_departure_date"));
        invoiceDto.setInvoiceStatus(InvoiceStatus.NEW.toString());

        Date currentArrivalDate = TimeConverter.convertStringToDate(request.getParameter("request_room_arrival_date"), "dd.MM.yyyy");
        Date currentDepartureDate = TimeConverter.convertStringToDate(request.getParameter("request_room_departure_date"), "dd.MM.yyyy");
        int countOfDay = TimeConverter.differenceBtwTwoDatesInDays(currentArrivalDate, currentDepartureDate) - 1;
        invoiceDto.setTotalPrice(Integer.valueOf(request.getParameter("price_" + numberOfLine).trim()) * 100 * countOfDay);

        return invoiceDto;
    }
}
