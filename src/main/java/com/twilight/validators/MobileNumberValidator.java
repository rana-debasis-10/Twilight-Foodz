package com.twilight.validators;

import com.twilight.annotations.ValidMobileNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class MobileNumberValidator implements ConstraintValidator<ValidMobileNumber, String> {

    // Regex for 10-digit Indian mobile numbers starting with 6, 7, 8, or 9
    private static final String MOBILE_PATTERN = "^[6-9]\\d{9}$";

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return false;
        }
        return Pattern.matches(MOBILE_PATTERN, mobileNumber);
    }
}
