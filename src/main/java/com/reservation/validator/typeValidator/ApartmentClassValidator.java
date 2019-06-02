package com.reservation.validator.typeValidator;

import com.reservation.model.enums.ApartmentСlass;

public class ApartmentClassValidator implements Validator<String> {

    private String message;

    public ApartmentClassValidator(String message) {
        this.message = message;
    }

    @Override
    public boolean validateValue(String value) {

        for (ApartmentСlass apartmentСlass : ApartmentСlass.values()) {
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
