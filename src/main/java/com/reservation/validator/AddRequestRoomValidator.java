package com.reservation.validator;

import com.reservation.model.dto.RequestRoomDto;
import com.reservation.validator.typeValidator.ApartmentClassValidator;
import com.reservation.validator.typeValidator.NumberValidator;
import com.reservation.validator.typeValidator.RequestRoomStatusValidator;
import com.reservation.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class AddRequestRoomValidator extends ModelValidator<RequestRoomDto> {

    public AddRequestRoomValidator() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        NumberValidator placeNumberValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong place number");
        StringValidator arrivalDateValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong arrival date");
        StringValidator departureDateValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong departure date");
        RequestRoomStatusValidator requestRoomStatusValidator = new RequestRoomStatusValidator("Wrong request room status");
        ApartmentClassValidator apartmentClassValidator = new ApartmentClassValidator("Wrong apartment class");
        validators.put(placeNumberValidator, RequestRoomDto::getPlaceNumber);
        validators.put(arrivalDateValidator, RequestRoomDto::getArrivalDate);
        validators.put(departureDateValidator, RequestRoomDto::getDepartureDate);
        validators.put(requestRoomStatusValidator, RequestRoomDto::getRequestRoomStatus);
        validators.put(apartmentClassValidator, RequestRoomDto::getApartmentClass);
    }
}


