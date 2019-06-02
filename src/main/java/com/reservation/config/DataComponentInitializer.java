package com.reservation.config;

import com.reservation.model.converter.resultSetConverter.RequestRoomResultSetConverter;
import com.reservation.model.converter.resultSetConverter.RoomResultSetConverter;
import com.reservation.model.converter.resultSetConverter.UserResultSetConverter;
import com.reservation.model.dao.DataSource;
import com.reservation.model.dao.RequestRoomDao;
import com.reservation.model.dao.RoomDao;
import com.reservation.model.dao.UserDao;

public class DataComponentInitializer {

    private static DataComponentInitializer initializer;

    private final DataSource dataSource;
    private final UserDao userDao;
    private final RoomDao roomDao;
    private final RequestRoomDao requestRoomDao;

    private final UserResultSetConverter userResultSetConverter;
    private final RoomResultSetConverter roomResultSetConverter;
    private final RequestRoomResultSetConverter requestRoomResultSetConverter;

    private DataComponentInitializer() {

        dataSource = new DataSource();

        userResultSetConverter = new UserResultSetConverter();
        roomResultSetConverter = new RoomResultSetConverter();
        requestRoomResultSetConverter = new RequestRoomResultSetConverter(userResultSetConverter);

        userDao = new UserDao(dataSource, userResultSetConverter);
        roomDao = new RoomDao(dataSource, roomResultSetConverter);
        requestRoomDao = new RequestRoomDao(dataSource, requestRoomResultSetConverter);
    }

    public static DataComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (DataComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new DataComponentInitializer();
                }
            }
        }

        return initializer;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserResultSetConverter getUserResultSetConverter() {
        return userResultSetConverter;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public RequestRoomDao getRequestRoomDao() {
        return requestRoomDao;
    }
}
