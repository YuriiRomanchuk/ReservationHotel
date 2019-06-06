package com.reservation.config;

import com.reservation.model.converter.resultSetConverter.InvoiceResultSetConverter;
import com.reservation.model.converter.resultSetConverter.RequestRoomResultSetConverter;
import com.reservation.model.converter.resultSetConverter.RoomResultSetConverter;
import com.reservation.model.converter.resultSetConverter.UserResultSetConverter;
import com.reservation.model.dao.*;

public class DataComponentInitializer {

    private static DataComponentInitializer initializer;

    private final DataSource dataSource;
    private final UserDao userDao;
    private final RoomDao roomDao;
    private final RequestRoomDao requestRoomDao;
    private final InvoiceDao invoiceDao;

    private final UserResultSetConverter userResultSetConverter;
    private final RoomResultSetConverter roomResultSetConverter;
    private final RequestRoomResultSetConverter requestRoomResultSetConverter;
    private final InvoiceResultSetConverter invoiceResultSetConverter;

    private DataComponentInitializer() {

        dataSource = new DataSource();

        userResultSetConverter = new UserResultSetConverter();
        roomResultSetConverter = new RoomResultSetConverter();
        requestRoomResultSetConverter = new RequestRoomResultSetConverter(userResultSetConverter);
        invoiceResultSetConverter = new InvoiceResultSetConverter(userResultSetConverter, roomResultSetConverter, requestRoomResultSetConverter);

        userDao = new UserDao(dataSource, userResultSetConverter);
        roomDao = new RoomDao(dataSource, roomResultSetConverter);
        requestRoomDao = new RequestRoomDao(dataSource, requestRoomResultSetConverter);
        invoiceDao = new InvoiceDao(dataSource, invoiceResultSetConverter);
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

    public InvoiceDao getInvoiceDao() {
        return invoiceDao;
    }
}
