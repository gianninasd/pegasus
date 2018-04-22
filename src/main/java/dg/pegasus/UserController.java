package dg.pegasus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main REST controller for processing all user administration requests.
 * @author gianninasd
 */
@RestController
public class UserController {

  @RequestMapping("/getUser")
  public User getUser() {
    User user = new User();
    user.setId(5);
    user.setName("Mary K.");
    user.setUserName("mary");
    return user;
  }
}