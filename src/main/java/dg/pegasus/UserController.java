package dg.pegasus;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main REST controller for processing all user administration requests.
 * @author gianninasd
 */
@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/getUser")
  public User getUser() {
    Optional<User> user = userRepository.findById("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d");
    return user.get();
  }
}