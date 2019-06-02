package com.reservation.validator.typeValidator;

import com.reservation.model.enums.RequestRoomStatus;

public class RequestRoomStatusValidator implements Validator<String> {

    private String message;

    public RequestRoomStatusValidator(String message) {
        this.message = message;
    }

    @Override
    public boolean validateValue(String value) {

        for (RequestRoomStatus apartmentСlass : RequestRoomStatus.values()) {
            if (apartmentСlass.name().equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
