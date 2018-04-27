package dg.pegasus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main REST controller for processing all user administration requests.
 * @author gianninasd
 */
@RestController
@RequestMapping(path="/user")
public class UserController {

  private static final Logger logger = LogManager.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping(method=RequestMethod.GET)
  public User load( @RequestParam String id ) {
    logger.info("Loading user " + id);
    return userService.load( id );
  }

  @RequestMapping(method=RequestMethod.POST)
  public @ResponseBody String create( @RequestBody User user ) {
    userService.create(user);
    return "Saved";
  }
}