package com.reservation.controller;

import com.reservation.exception.ServiceException;
import com.reservation.model.dto.UserDto;
import com.reservation.model.entity.User;
import com.reservation.model.enums.Role;
import com.reservation.service.RequestRoomService;
import com.reservation.service.UserService;
import com.reservation.validator.UserLoginValidator;
import com.reservation.validator.UserRegistrationDataValidator;
import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;
import com.reservation.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserController {


    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private final UserService userService;
    private final RequestRoomService requestRoomService;
    private final UserRegistrationDataValidator userRegistrationDataValidator;
    private final UserLoginValidator userLoginValidator;

    public UserController(UserService userService,
                          RequestRoomService requestRoomService,
                          UserRegistrationDataValidator userRegistrationDataValidator,
                          UserLoginValidator userLoginValidator) {
        this.userService = userService;
        this.requestRoomService = requestRoomService;
        this.userRegistrationDataValidator = userRegistrationDataValidator;
        this.userLoginValidator = userLoginValidator;
    }

    public View showRegistrationPage() {
        return new ViewModel("WEB-INF/jsp/registration-form.jsp");
    }

    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/jsp/login.jsp");
    }

    public View showAdminPersonalArea() {
        View view;
        /*    try {*/
        view = new ViewModel("WEB-INF/jsp/admin/admin-personal-area.jsp");
        /*      view.addParameter("filmSaleDto", filmSessionService.receiveFilmSalesByDate(currentDate));*/
/*            LOGGER.debug("show admin personal area");
        } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("Admin personal area is not shown!" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }*/
        return view;
    }

    public View showUserPersonalArea(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-personal-area.jsp");
            view.addParameter("requestsRoom", requestRoomService.receiveRequestsRoomByUserId(userDto.getId()));
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View logout() {
        LOGGER.debug("User logout");
        return new RedirectViewModel(new ViewModel("index"));
    }

    public View loginUser(UserDto userDto) {
        View view;
        try {
            view = validateLoginUser(userDto);
            LOGGER.debug("User login");
        } catch (ServiceException e) {
            view = receiveViewModel("login", e.getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View createUser(UserDto userDto) {
        View view;
        try {
            view = validateRegistrationUser(userDto);
            LOGGER.debug("User create");
        } catch (ServiceException e) {
            view = receiveViewModel("registration-form", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("User is not created" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    private View validateLoginUser(UserDto userDto) throws ServiceException {
        View view;
        String invalidateFields = userLoginValidator.validate(userDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("login", invalidateFields);
        } else {
            User user = userService.loginUser(userDto);
            view = receiveViewModel(user.getRole().equals(Role.ADMIN) ? "admin-personal-area" : "user-personal-area", "");
        }
        return view;
    }

    private View validateRegistrationUser(UserDto userDto) throws ServiceException {
        View view;
        String invalidateFields = userRegistrationDataValidator.validate(userDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("registration-form", invalidateFields);
            view.addParameter("userDto", userDto);
        } else {
            userService.createUser(userDto);
            view = receiveViewModel("login", "User created!");
        }
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        LOGGER.debug(error);
        return view;
    }
}
