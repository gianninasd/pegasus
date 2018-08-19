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

      // generate salt and hash the password
      String salt = PasswordUtils.generateSalt();
      user.setPassword( PasswordUtils.hashN(user.getPassword(), salt) );
      user.setPasswordSalt( salt );
      
      return userRepository.save(user);
    }
    catch( Exception ex ) {
      throw new UserSaveFailedException(ex);
    }
  }

  /**
   * Updates an existing user using the data provided.
   * @throws UserNotFoundException if the user cannot be found based on the id
   */
  public User update( String id, User user ) {
    Optional<User> user2Edit = userRepository.findById( id );

    if( !user2Edit.isPresent() ) {
      throw new UserNotFoundException( id );
    }

    try {
      user.setId(id);
      user.setPassword(user2Edit.get().getPassword());
      user.setPasswordSalt(user2Edit.get().getPasswordSalt());
      user.setCreatedBy(user2Edit.get().getCreatedBy());
      user.setCreationDate(user2Edit.get().getCreationDate());
      user.setModificationDate(new Date());
      
      return userRepository.save(user);
    }
    catch( Exception ex ) {
      throw new UserSaveFailedException(ex);
    }
  }
}