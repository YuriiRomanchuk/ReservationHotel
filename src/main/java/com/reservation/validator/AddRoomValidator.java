package com.reservation.validator;

import com.reservation.model.dto.RoomDto;
import com.reservation.validator.typeValidator.NumberValidator;
import com.reservation.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class AddRoomValidator extends ModelValidator<RoomDto> {

    public AddRoomValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        NumberValidator nameRoomNumberValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong room number");
        NumberValidator namePlaceNumberValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong place number");
        NumberValidator namePriceValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong price");
        StringValidator nameApartmentClassValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong apartment class");
        validators.put(nameRoomNumberValidator, RoomDto::getRoomNumber);
        validators.put(namePlaceNumberValidator, RoomDto::getPlaceNumber);
        validators.put(nameApartmentClassValidator, RoomDto::getApartmentClass);
        validators.put(namePriceValidator, RoomDto::getPrice);
    }
}
