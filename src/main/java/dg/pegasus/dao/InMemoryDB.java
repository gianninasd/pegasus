package dg.pegasus.dao;

import dg.pegasus.User;
import dg.pegasus.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores objects in memory, using a string as the object key.
 */
@Component("inMemoryDB")
public class InMemoryDB implements DataAccess {

  private Map<String, User> db = new HashMap<>();

  /**
   * Retrieves the object from the internal map based on the id specified.
   * @throws UserNotFoundException if the id does not locate an object
   */
  @Override
  public User get(String id ) {
    User object = db.get(id);

    if( object == null ) {
      throw new UserNotFoundException( id );
    }

    return object;
  }

  /**
   * Stores or updates the object provided.
   */
  @Override
  public User save( User user ) {
    db.put(user.getId(), user);
    return user;
  }
}
