package dg.pegasus.dao;

import dg.pegasus.User;
import dg.pegasus.UserNotFoundException;

/**
 * Interface to
 */
public interface DataAccess {

  /**
   * Retrieves the object from the internal map based on the id specified.
   * @throws UserNotFoundException if the id does not locate an object
   */
  public User get( String id );

  /**
   * Stores or updates the object provided.
   */
  public User save( User user );
}
