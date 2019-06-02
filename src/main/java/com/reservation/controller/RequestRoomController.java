package com.reservation.controller;

import com.reservation.exception.ServiceException;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.enums.ApartmentСlass;
import com.reservation.service.RequestRoomService;
import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;
import com.reservation.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestRoomController {

    private static final Logger LOGGER = LogManager.getLogger(RoomController.class);
    private final RequestRoomService requestRoomService;


    public RequestRoomController(RequestRoomService requestRoomService) {
        this.requestRoomService = requestRoomService;
    }

    public View createRoom(RequestRoomDto requestRoomDto) {
        View view;
        try {
            requestRoomService.createRequestRoom(requestRoomDto);
            view = receiveViewModel("user-personal-area", "Room added!");
        } catch (ServiceException e) {
            view = receiveViewModel("user-add-request-room", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            view.addParameter("requestRoomDto", requestRoomDto);
        }
        return new RedirectViewModel(view);
    }

    public View showAddRequestRoomPage() {
        View view = new ViewModel("WEB-INF/jsp/admin/user-add-request-room.jsp");
        int[] placesNumber = {1, 2, 3, 4};
        view.addParameter("apartmentsClass", ApartmentСlass.values());
        view.addParameter("placesNumber", placesNumber);
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        LOGGER.debug(error);
        view.addParameter("Error", error);
        return view;
    }

}