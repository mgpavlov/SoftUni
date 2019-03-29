package residentevil.util.validators;

import residentevil.util.validators.annotations.CreatorValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomCreatorValidator implements ConstraintValidator<CreatorValidation, String> {

    @Override
    public boolean isValid(String creatorStr, ConstraintValidatorContext constraintValidatorContext) {
        if (creatorStr == null) {
            return false;
        }

        return creatorStr.equals("Corp") || creatorStr.equals("corp.");
    }
}
