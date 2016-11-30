package app;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import model.User;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {}

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    System.out.println("PASSWORD CRAP");
    User user = (User) obj;
    return user.getPassword().equals(user.getPassword());
  }
}
