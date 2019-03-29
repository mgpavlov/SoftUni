package residentevil.util.validators;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidatorImpl implements residentevil.util.validators.Validator {

    private Validator validator;

    public ValidatorImpl() {
        this.validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <M> boolean isValid(M model) {
        return this.validator.validate(model).size() == 0;
    }
}
