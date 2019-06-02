package com.reservation.model.converter.dtoConverter;


import com.reservation.model.converter.Converter;
import com.reservation.model.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserDtoConverter implements Converter<HttpServletRequest, UserDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public UserDto convert(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setLogin(request.getParameter("login"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setLastName(request.getParameter("lastName"));
        userDto.setFirstName(request.getParameter("firstName"));
        userDto.setMiddleName(request.getParameter("middleName"));
        userDto.setEmail(request.getParameter("email"));
        userDto.setPhone(request.getParameter("phone"));
        LOGGER.debug("User dto is converted from request!");
        return userDto;
    }

    public UserDto convertFromRequestForUserId(HttpServletRequest request) {
        int userId = (Integer) request.getSession().getAttribute("user_id");
        LOGGER.debug("User dto is converted from id!");
        return convertForUserId(userId);
    }

    public UserDto convertForUserId(int userId) {
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        LOGGER.debug("User dto is converted from id!");
        return userDto;
    }
}


