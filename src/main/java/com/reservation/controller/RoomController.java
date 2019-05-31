package com.reservation.controller;

import com.reservation.exception.ServiceException;
import com.reservation.model.dto.RoomDto;
import com.reservation.model.enums.ApartmentСlass;
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
    private final AddRoomValidator addRoomValidator;

    public RoomController(RoomService roomService, AddRoomValidator addRoomValidator) {
        this.roomService = roomService;
        this.addRoomValidator = addRoomValidator;
    }

    public View createRoom(RoomDto roomDto) {

        View view;
        try {
            int[] placesNumber = {1, 2, 3, 4};
            view = validateAddRoom(roomDto);
            view.addParameter("apartmentsClass", ApartmentСlass.values());
            view.addParameter("placesNumber", placesNumber);
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
        return new ViewModel("WEB-INF/jsp/admin/admin-add-room.jsp");
    }

}
