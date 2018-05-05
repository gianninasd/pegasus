import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dg.pegasus.User;

/**
 * Class for all unit tests related to the User validation.
 * @author gianninasd
 */
public class UserValidationTest {

  private static ValidatorFactory validatorFactory;
  private static Validator validator;
 
  @BeforeClass
  public static void createValidator() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void close() {
    validatorFactory.close();
  }

  @Test
  public void shouldHaveNoViolations() {
    //given:
    User user = new User();
    user.setName("mary k.");
    user.setUserName("mary");
    user.setEmail("mary@gmail.com");
    user.setStatusCode("PENDING");

    //when:
    Set<ConstraintViolation<User>> violations = validator.validate(user);

    //then:
    assertTrue(violations.isEmpty());
  }

  @Test
  public void invalidName() {
    User user = new User();
    user.setName("");
    user.setUserName("mary");
    user.setEmail("mary@gmail.com");
    user.setStatusCode("PENDING");

    Set<ConstraintViolation<User>> violations = validator.validate(user);

    assertEquals(violations.size(), 1);
    ConstraintViolation<User> violation = violations.iterator().next();
    assertEquals("{user.name.invalid}", violation.getMessage());

    user.setName("reallyreallyreallyreallyreallyreallyreallyreallyreallylong");

    violations = validator.validate(user);

    assertEquals(violations.size(), 1);
    violation = violations.iterator().next();
    assertEquals("{user.name.invalid}", violation.getMessage());
  }
}