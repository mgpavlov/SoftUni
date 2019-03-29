package softuni.residentevil.web.validators;

import softuni.residentevil.annotations.IsBefore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IsBeforeValidator implements ConstraintValidator<IsBefore, LocalDate> {

  @Override
  public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
    boolean isInTheFuture = false;
    if (date == null) {
      return isInTheFuture;
    }

    LocalDate currentDate = LocalDate.now();
    isInTheFuture = date.isBefore(currentDate);
    return isInTheFuture;
  }
}
