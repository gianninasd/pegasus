package dg.pegasus;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dg.pegasus.domain.ErrorResponse;

/**
 * Main REST controller for processing all user administration requests.
 * @author gianninasd
 */
@RestController
@RequestMapping(path="/users/v1/user")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  /**
   * Initialize the custom validators used by various methods.
   */
  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    //binder.addValidators(new CreateUserValidator());
  }

  /**
   * Loads a user based on the Id provided.
   */
  @RequestMapping(method=RequestMethod.GET, value="/{id}")
  public User load( @PathVariable String id ) {
    logger.info("Loading user " + id);
    return userService.load( id );
  }

  /**
   * Creates a user based on the data provided.
   */
  @RequestMapping(method=RequestMethod.POST)
  public @ResponseBody Object create( @Valid @RequestBody User user, Errors errors ) {
    logger.info("Create user: " + user);

    if( errors.getErrorCount() > 0 ) {
      ErrorResponse res = new ErrorResponse("2001", "Some fields contain errors");

      for( FieldError err: errors.getFieldErrors() ) {
        res.getError().addDetails(err.getField(), err.getDefaultMessage());
      }

      return res;
    }
    else {
      userService.create(user);
      return "Saved";
    }
  }

  /**
   * Handles when a UserNotFoundException is thrown and returns a proper error response.
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public ErrorResponse handleUserNotFound(UserNotFoundException ex) {
    logger.warn("Cannot load user " + ex.getId());
    return new ErrorResponse("2000", "User not found");
  }
}