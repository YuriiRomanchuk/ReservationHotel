package com.reservation.service;

import com.reservation.model.converter.entityConverter.UserConverter;
import com.reservation.model.dao.UserDao;
import com.reservation.model.dto.UserDto;
import com.reservation.model.enums.Role;

public class UserService {

    private final UserDao userDao;
    private final UserConverter userConverter;

    public UserService(UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    public Role receiveUserRole(UserDto userDto) {
        return null;
    }

    public int receiveUserId(UserDto userDto) {
        return 0;
    }
}
