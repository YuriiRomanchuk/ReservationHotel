package com.reservation.config;

import com.reservation.controller.ChangeLanguageController;
import com.reservation.controller.UserController;
import com.reservation.controller.WelcomeController;
import com.reservation.model.converter.UserDtoConverter;
import com.reservation.model.converter.UserLoginDtoConverter;
import com.reservation.model.converter.entityConverter.UserConverter;
import com.reservation.service.UserService;
import com.reservation.servlet.RequestResolver;
import com.reservation.validator.UserLoginValidator;
import com.reservation.validator.UserRegistrationDataValidator;

public class WebComponentInitializer {

    private static WebComponentInitializer initializer;
    private final RequestResolver requestResolver;

    private final WelcomeController welcomeController;
    private final UserController userController;
    private final ChangeLanguageController changeLanguageController;

    private final UserService userService;

    private final UserLoginDtoConverter userLoginDtoConverter;
    private final UserDtoConverter userDtoConverter;

    private final UserRegistrationDataValidator userRegistrationValidator;
    private final UserLoginValidator userLoginValidator;

    private final UserConverter userConverter;

    private WebComponentInitializer() {

        DataComponentInitializer dataComponentInitializer = DataComponentInitializer.getInstance();


        userRegistrationValidator = new UserRegistrationDataValidator();
        userLoginValidator = new UserLoginValidator();

        userLoginDtoConverter = new UserLoginDtoConverter();
        userDtoConverter = new UserDtoConverter();

        userConverter = new UserConverter();

        userService = new UserService(dataComponentInitializer.getUserDao(), userConverter);

        requestResolver = new RequestResolver(this);

        changeLanguageController = new ChangeLanguageController();
        welcomeController = new WelcomeController();
        userController = new UserController();

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
        return null;
    }

    public UserService getUserService() {
        return null;
    }

    public WelcomeController getWelcomeController() {
        return welcomeController;
    }
}

