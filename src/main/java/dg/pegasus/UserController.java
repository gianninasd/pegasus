package dg.pegasus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
   * Loads a user based on the Id provided.
   */
  @RequestMapping(method=RequestMethod.GET, value="/{id}")
  public User load( @PathVariable String id ) {
    logger.info("Loading user " + id);
    return userService.load( id );
  }

  @RequestMapping(method=RequestMethod.POST)
  public @ResponseBody String create( @RequestBody User user ) {
    userService.create(user);
    return "Saved";
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