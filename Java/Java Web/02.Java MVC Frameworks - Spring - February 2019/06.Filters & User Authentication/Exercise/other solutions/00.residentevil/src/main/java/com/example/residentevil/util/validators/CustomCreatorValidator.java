package com.example.residentevil.util.validators;

import com.example.residentevil.util.validators.annotations.CreatorValidation;
import com.example.residentevil.util.validators.annotations.DateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CustomCreatorValidator implements ConstraintValidator<CreatorValidation, String> {

    @Override
    public boolean isValid(String creatorStr, ConstraintValidatorContext constraintValidatorContext) {
        if (creatorStr == null) {
            return false;
        }

        return creatorStr.equals("Corp") || creatorStr.equals("corp.");
    }
}
