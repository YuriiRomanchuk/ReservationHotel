package com.reservation.config;

import com.reservation.controller.ChangeLanguageController;
import com.reservation.controller.RoomController;
import com.reservation.controller.UserController;
import com.reservation.controller.WelcomeController;
import com.reservation.model.converter.dtoConverter.RoomDtoConverter;
import com.reservation.model.converter.dtoConverter.UserDtoConverter;
import com.reservation.model.converter.dtoConverter.UserLoginDtoConverter;
import com.reservation.model.converter.entityConverter.RoomConverter;
import com.reservation.model.converter.entityConverter.UserConverter;
import com.reservation.service.RoomService;
import com.reservation.service.UserService;
import com.reservation.servlet.RequestResolver;
import com.reservation.validator.AddRoomValidator;
import com.reservation.validator.UserLoginValidator;
import com.reservation.validator.UserRegistrationDataValidator;

public class WebComponentInitializer {

    private static WebComponentInitializer initializer;
    private final RequestResolver requestResolver;

    private final WelcomeController welcomeController;
    private final UserController userController;
    private final ChangeLanguageController changeLanguageController;
    private final RoomController roomController;

    private final UserService userService;
    private final RoomService roomService;

    private final UserLoginDtoConverter userLoginDtoConverter;
    private final UserDtoConverter userDtoConverter;
    private final RoomDtoConverter roomDtoConverter;

    private final UserRegistrationDataValidator userRegistrationValidator;
    private final UserLoginValidator userLoginValidator;
    private final AddRoomValidator addRoomValidator;

    private final UserConverter userConverter;
    private final RoomConverter roomConverter;

    private WebComponentInitializer() {

        DataComponentInitializer dataComponentInitializer = DataComponentInitializer.getInstance();


        userRegistrationValidator = new UserRegistrationDataValidator();
        userLoginValidator = new UserLoginValidator();
        addRoomValidator = new AddRoomValidator();

        userLoginDtoConverter = new UserLoginDtoConverter();
        userDtoConverter = new UserDtoConverter();
        roomDtoConverter = new RoomDtoConverter();

        userConverter = new UserConverter();
        roomConverter = new RoomConverter();

        userService = new UserService(dataComponentInitializer.getUserDao(), userConverter);
        roomService = new RoomService(dataComponentInitializer.getRoomDao(), roomConverter, roomDtoConverter);

        changeLanguageController = new ChangeLanguageController();
        welcomeController = new WelcomeController();
        userController = new UserController(userService, userRegistrationValidator, userLoginValidator);
        roomController = new RoomController(roomService, addRoomValidator);

        requestResolver = new RequestResolver(this);
    }

    public static WebComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (WebComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new WebComponentInitializer();
                }
            }
        }

        return initializer;
    }

    public RequestResolver getRequestResolver() {
        return requestResolver;
    }

    public UserLoginDtoConverter getUserLoginDtoConverter() {
        return userLoginDtoConverter;
    }

    public UserService getUserService() {
        return userService;
    }

    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    public UserController getUserController() {
        return userController;
    }

    public UserDtoConverter getUserDtoConverter() {
        return userDtoConverter;
    }

    public ChangeLanguageController getChangeLanguageController() {
        return changeLanguageController;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    public RoomDtoConverter getRoomDtoConverter() {
        return roomDtoConverter;
    }
}

