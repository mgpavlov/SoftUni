package com.example.residentevil.util.validators.annotations;

import com.example.residentevil.util.validators.CustomDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = CustomDateValidator.class)
public @interface DateValidation {

    String message() default "Date cannot be empty, should be before the “today” date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
