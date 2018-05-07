package dg.pegasus.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation so we can execute the associated validator.
 * @author gianninasd
 */
@Documented
@Constraint(validatedBy = UserStatusValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserStatusConstraint {
  String message() default "Invalid user status";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}