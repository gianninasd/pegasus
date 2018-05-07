package dg.pegasus.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dg.pegasus.domain.UserStatus;

/**
 * Validates that the value supplied is one of the enumerations.
 * @author gianninasd
 */
public class UserStatusValidator implements ConstraintValidator<UserStatusConstraint,String> {
  
  @Override
  public void initialize( UserStatusConstraint userStatus ) {
  }

  @Override
  public boolean isValid( String userStatus, ConstraintValidatorContext cxt ) {
    try {
      UserStatus.valueOf(userStatus);
      return true;
    }
    catch( IllegalArgumentException ex ) {
      return false;
    }
  }
}