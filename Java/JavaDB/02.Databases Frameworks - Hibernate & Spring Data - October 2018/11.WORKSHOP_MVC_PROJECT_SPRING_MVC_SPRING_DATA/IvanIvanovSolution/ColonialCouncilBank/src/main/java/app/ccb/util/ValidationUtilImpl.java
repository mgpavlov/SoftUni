package app.ccb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).size() == 0;
    }
}
