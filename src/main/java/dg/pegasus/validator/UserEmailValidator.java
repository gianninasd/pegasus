package dg.pegasus.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Validates that the value supplied is a valid email address.
 * @author gianninasd
 */
public class UserEmailValidator implements ConstraintValidator<EmailConstraint,String> {
  
  @Override
  public void initialize( EmailConstraint email ) {
  }

  @Override
  public boolean isValid( String email, ConstraintValidatorContext cxt ) {
    return EmailValidator.getInstance().isValid(email);
  }
}