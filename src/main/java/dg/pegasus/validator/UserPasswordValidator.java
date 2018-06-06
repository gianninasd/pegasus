package dg.pegasus.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * Validates that the value supplied is a valid password.
 * @author gianninasd
 */
public class UserPasswordValidator implements ConstraintValidator<PasswordConstraint,String> {
  
  @Override
  public void initialize( PasswordConstraint password ) {
  }

  @Override
  public boolean isValid( String password, ConstraintValidatorContext cxt ) {
    boolean alphaNumeric = StringUtils.isAlphanumeric(password);
    boolean notBlank = StringUtils.isNotBlank(password);
    boolean mixedCase = StringUtils.isMixedCase(password);

    int length = StringUtils.length(password);
    boolean lengthGood = length >= 8 && length <= 50;

    return (lengthGood && notBlank && alphaNumeric && mixedCase);
  }
}