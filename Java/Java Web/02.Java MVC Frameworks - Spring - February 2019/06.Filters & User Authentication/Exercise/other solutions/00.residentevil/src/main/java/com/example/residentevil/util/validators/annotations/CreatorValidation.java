package com.example.residentevil.util.validators.annotations;

import com.example.residentevil.util.validators.CustomCreatorValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = CustomCreatorValidator.class)
public @interface CreatorValidation {

    String message() default "Should be either Corp or corp. Cannot be empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
