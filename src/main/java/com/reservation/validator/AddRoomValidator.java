package com.reservation.validator;

import com.reservation.model.dto.RoomDto;
import com.reservation.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class AddRoomValidator extends ModelValidator<RoomDto> {

    public AddRoomValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator nameRoomNumberValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong room number");
        StringValidator namePlaceNumber = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong place number");
        StringValidator nameApartmentСlass = new StringValidator(resourceBundle.getString("regexString"), "Wrong apartment class");
        validators.put(nameRoomNumberValidator, RoomDto::getRoomNumber);
        validators.put(namePlaceNumber, RoomDto::getPlaceNumber);
        validators.put(nameApartmentСlass, RoomDto::getApartmentСlass);
    }
}
