package dg.pegasus;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main REST controller for processing all user administration requests.
 * @author gianninasd
 */
@RestController
@RequestMapping(path="/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/getUser")
  public User getUser() {
    Optional<User> user = userRepository.findById("dd7262ad-f713-4d36-bcb3-fe9b5e75a74d");
    return user.get();
  }

  @RequestMapping("/create")
  public @ResponseBody String create( @RequestBody User user ) {
    // TODO add validation
    // TODO move this to service class
    UUID id = UUID.randomUUID();
    user.setId(id.toString());
    user.setCreatedBy("API");
    user.setCreationDate(new Date());
    userRepository.save(user);
    return "Saved";
  }
}