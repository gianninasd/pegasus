package dg.pegasus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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