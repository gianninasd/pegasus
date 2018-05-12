package dg.pegasus;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for all User administration operations.
 * @author gianninasd
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Returns a user object based on the id provided.
   * @throws UserNotFoundException if the user cannot be found
   */
  public User load( String id ) {
    Optional<User> user = userRepository.findById( id );

    if( !user.isPresent() ) {
      throw new UserNotFoundException( id );
    }

    return user.get();
  }

  /**
   * Creates and stores a new user using the data provided.
   * @throws UserSaveFailedException if an unknown error occured
   */
  public User create( User user ) {
    try {
      UUID id = UUID.randomUUID();
      user.setId(id.toString());
      user.setCreatedBy("API");
      user.setCreationDate(new Date());
      user.setModifiedBy("API");
      user.setModificationDate(new Date());
      user.setPassword("dummy");
      user.setPasswordSalt("salt");
      return userRepository.save(user);
    }
    catch( Exception ex ) {
      throw new UserSaveFailedException(ex.getMessage());
    }
  }
}