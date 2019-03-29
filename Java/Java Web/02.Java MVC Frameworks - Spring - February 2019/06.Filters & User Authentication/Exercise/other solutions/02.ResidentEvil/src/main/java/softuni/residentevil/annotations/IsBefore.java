package softuni.residentevil.annotations;

import softuni.residentevil.web.validators.IsBeforeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsBeforeValidator.class)
public @interface IsBefore {
  String message() default "Invalid Date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
