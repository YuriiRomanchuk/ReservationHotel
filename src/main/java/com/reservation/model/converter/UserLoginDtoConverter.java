package com.reservation.model.converter;

import com.reservation.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserLoginDtoConverter implements Converter<HttpServletRequest, UserDto> {

    @Override
    public UserDto convert(HttpServletRequest object) {
        return null;
    }
}
