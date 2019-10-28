package dg.pegasus;

import java.util.Date;
import java.util.UUID;

import dg.pegasus.dao.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service class for all User administration operations.
 * @author gianninasd
 */
@Service
public class UserService {

  @Autowired
  @Qualifier("inMemoryDB")
  private DataAccess dataAccess;

  /**
   * Returns a user object based on the id provided.
   * @throws UserNotFoundException if the user cannot be found
   */
  public User load( String id ) {
    return dataAccess.get(id);
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
      
      return dataAccess.save(user);
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
    User user2Edit = dataAccess.get(id);

    try {
      user.setId(id);
      user.setPassword(user2Edit.getPassword());
      user.setPasswordSalt(user2Edit.getPasswordSalt());
      user.setCreatedBy(user2Edit.getCreatedBy());
      user.setCreationDate(user2Edit.getCreationDate());
      user.setModificationDate(new Date());
      
      return dataAccess.save(user);
    }
    catch( Exception ex ) {
      throw new UserSaveFailedException(ex);
    }
  }
}