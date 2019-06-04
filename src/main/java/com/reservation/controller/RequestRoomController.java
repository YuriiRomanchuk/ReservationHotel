package com.reservation.controller;

import com.reservation.exception.ServiceException;
import com.reservation.model.dto.RequestRoomDto;
import com.reservation.model.enums.ApartmentСlass;
import com.reservation.service.RequestRoomService;
import com.reservation.validator.AddRequestRoomValidator;
import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;
import com.reservation.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestRoomController {

    private static final Logger LOGGER = LogManager.getLogger(RoomController.class);
    private final RequestRoomService requestRoomService;
    private final AddRequestRoomValidator addRequestRoomValidator;

    public RequestRoomController(RequestRoomService requestRoomService, AddRequestRoomValidator addRequestRoomValidator) {
        this.requestRoomService = requestRoomService;
        this.addRequestRoomValidator = addRequestRoomValidator;
    }

    public View createRequestRoom(RequestRoomDto requestRoomDto) {
        View view;
        try {
            view = validateAddRequestRoom(requestRoomDto);
        } catch (ServiceException e) {
            view = receiveViewModel("user-add-request-room", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            view.addParameter("requestRoomDto", requestRoomDto);
        }
        return new RedirectViewModel(view);
    }

    public View showAddRequestRoomPage() {
        View view = new ViewModel("WEB-INF/jsp/user/user-add-request-room.jsp");
        int[] placesNumber = {1, 2, 3, 4};
        view.addParameter("apartmentsClass", ApartmentСlass.values());
        view.addParameter("placesNumber", placesNumber);
        return view;
    }

    public View showRequestTreatment() {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-request-treatment.jsp");
            view.addParameter("requestRooms", requestRoomService.receiveNewRequestRooms());
        } catch (ServiceException e) {
            view = receiveViewModel("admin-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return view;
    }

    private View validateAddRequestRoom(RequestRoomDto requestRoomDto) throws ServiceException {
        View view;
        String invalidateFields = addRequestRoomValidator.validate(requestRoomDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("user-add-request-room", invalidateFields);
            view.addParameter("requestRoomDto", requestRoomDto);
        } else {
            requestRoomService.createRequestRoom(requestRoomDto);
            view = receiveViewModel("user-personal-area", "Request added!");
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

    public View showRoomSelectionAdmin(int requestRoomId) {
        return new RedirectViewModel(new ViewModel("admin-room-selection/" + requestRoomId));
    }


}
