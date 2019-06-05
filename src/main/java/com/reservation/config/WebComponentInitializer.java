package com.reservation.config;

import com.reservation.controller.*;
import com.reservation.model.converter.dtoConverter.*;
import com.reservation.model.converter.entityConverter.InvoiceConverter;
import com.reservation.model.converter.entityConverter.RequestRoomConverter;
import com.reservation.model.converter.entityConverter.RoomConverter;
import com.reservation.model.converter.entityConverter.UserConverter;
import com.reservation.service.InvoiceService;
import com.reservation.service.RequestRoomService;
import com.reservation.service.RoomService;
import com.reservation.service.UserService;
import com.reservation.servlet.RequestResolver;
import com.reservation.validator.AddRequestRoomValidator;
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
    private final RequestRoomController requestRoomController;
    private final InvoiceController invoiceController;

    private final UserService userService;
    private final RoomService roomService;
    private final RequestRoomService requestRoomService;
    private final InvoiceService invoiceService;

    private final UserLoginDtoConverter userLoginDtoConverter;
    private final UserDtoConverter userDtoConverter;
    private final RoomDtoConverter roomDtoConverter;
    private final RequestRoomDtoConverter requestRoomDtoConverter;
    private final UserDtoFromEntityConverter userDtoFromEntityConverter;
    private final RequestRoomDtoFromEntityConverter requestRoomDtoFromEntityConverter;
    private final InvoiceDtoConverter invoiceDtoConverter;

    private final UserRegistrationDataValidator userRegistrationValidator;
    private final UserLoginValidator userLoginValidator;
    private final AddRoomValidator addRoomValidator;
    private final AddRequestRoomValidator addRequestRoomValidator;

    private final UserConverter userConverter;
    private final RoomConverter roomConverter;
    private final RequestRoomConverter requestRoomConverter;
    private final InvoiceConverter invoiceConverter;

    private WebComponentInitializer() {

        DataComponentInitializer dataComponentInitializer = DataComponentInitializer.getInstance();

        userRegistrationValidator = new UserRegistrationDataValidator();
        userLoginValidator = new UserLoginValidator();
        addRoomValidator = new AddRoomValidator();
        addRequestRoomValidator = new AddRequestRoomValidator();

        userLoginDtoConverter = new UserLoginDtoConverter();
        userDtoConverter = new UserDtoConverter();
        roomDtoConverter = new RoomDtoConverter();
        requestRoomDtoConverter = new RequestRoomDtoConverter(userDtoConverter);
        userDtoFromEntityConverter = new UserDtoFromEntityConverter();
        requestRoomDtoFromEntityConverter = new RequestRoomDtoFromEntityConverter(userDtoFromEntityConverter);
        invoiceDtoConverter = new InvoiceDtoConverter(userDtoConverter, roomDtoConverter, requestRoomDtoConverter);

        userConverter = new UserConverter();
        roomConverter = new RoomConverter();
        requestRoomConverter = new RequestRoomConverter(userConverter);
        invoiceConverter = new InvoiceConverter(userConverter, roomConverter, requestRoomConverter);

        userService = new UserService(dataComponentInitializer.getUserDao(), userConverter);
        roomService = new RoomService(dataComponentInitializer.getRoomDao(), roomConverter, roomDtoConverter);
        requestRoomService = new RequestRoomService(dataComponentInitializer.getRequestRoomDao(), requestRoomConverter, requestRoomDtoFromEntityConverter);
        invoiceService = new InvoiceService(dataComponentInitializer.getInvoiceDao(), invoiceConverter, invoiceDtoConverter);

        changeLanguageController = new ChangeLanguageController();
        welcomeController = new WelcomeController();
        userController = new UserController(userService, userRegistrationValidator, userLoginValidator);
        roomController = new RoomController(roomService, requestRoomService, addRoomValidator);
        requestRoomController = new RequestRoomController(requestRoomService, addRequestRoomValidator);
        invoiceController = new InvoiceController(invoiceService, requestRoomService, roomService);

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

    public RequestRoomController getRequestRoomController() {
        return requestRoomController;
    }

    public RequestRoomDtoConverter getRequestRoomDtoConverter() {
        return requestRoomDtoConverter;
    }


}

