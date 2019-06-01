package com.reservation.validator;

import com.reservation.model.dto.RoomDto;
import com.reservation.validator.typeValidator.NumberValidator;
import com.reservation.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class AddRoomValidator extends ModelValidator<RoomDto> {

    public AddRoomValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        NumberValidator nameRoomNumberValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong room number");
        NumberValidator namePlaceNumber = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong place number");
        NumberValidator namePrice = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong place number");
        StringValidator nameApartmentClass = new StringValidator(resourceBundle.getString("regexString"), "Wrong apartment class");
        validators.put(nameRoomNumberValidator, RoomDto::getRoomNumber);
        validators.put(namePlaceNumber, RoomDto::getPlaceNumber);
        validators.put(nameApartmentClass, RoomDto::getApartmentСlass);
        validators.put(namePrice, RoomDto::getPrice);
    }
}
