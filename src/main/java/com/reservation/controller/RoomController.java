package com.reservation.controller;

import com.reservation.exception.ServiceException;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.dto.RoomDto;
import com.reservation.model.enums.ApartmentСlass;
import com.reservation.service.RequestRoomService;
import com.reservation.service.RoomService;
import com.reservation.validator.AddRoomValidator;
import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;
import com.reservation.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomController {

    private static final Logger LOGGER = LogManager.getLogger(RoomController.class);
    private final RoomService roomService;
    private final RequestRoomService requestRoomService;
    private final AddRoomValidator addRoomValidator;

    public RoomController(RoomService roomService, RequestRoomService requestRoomService, AddRoomValidator addRoomValidator) {
        this.roomService = roomService;
        this.requestRoomService = requestRoomService;
        this.addRoomValidator = addRoomValidator;
    }

    public View createRoom(RoomDto roomDto) {

        View view;
        try {
            view = validateAddRoom(roomDto);
        } catch (ServiceException e) {
            view = receiveViewModel("admin-add-room", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            view.addParameter("roomDto", roomDto);
        }
        return new RedirectViewModel(view);
    }

    private View validateAddRoom(RoomDto roomDto) throws ServiceException {
        View view;
        String invalidateFields = addRoomValidator.validate(roomDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("admin-add-room", invalidateFields);
            view.addParameter("roomDto", roomDto);
        } else {
            roomService.createRoom(roomDto);
            view = receiveViewModel("admin-personal-area", "Room added!");
        }
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        LOGGER.debug(error);
        view.addParameter("Error", error);
        return view;
    }

    public View showAddRoomPage() {
        View view = new ViewModel("WEB-INF/jsp/admin/admin-add-room.jsp");
        int[] placesNumber = {1, 2, 3, 4};
        view.addParameter("apartmentsClass", ApartmentСlass.values());
        view.addParameter("placesNumber", placesNumber);
        return view;
    }

    public View showRoomSelectionPage(int requestRoomId) {
        View view;
        try {
            RequestRoomDto requestRoomDto = requestRoomService.receiveRequestRoomById(requestRoomId);
            view = new ViewModel("WEB-INF/jsp/admin/admin-room-selection.jsp");
            view.addParameter("requestRoomDto", requestRoomDto);
            view.addParameter("rooms", roomService.receiveFreeRoomsByParameters(requestRoomDto.getApartmentClass(), requestRoomDto.getArrivalDate(),
                    requestRoomDto.getDepartureDate(), requestRoomDto.getPlaceNumber()));
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("Session room is not shown! " + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

}
