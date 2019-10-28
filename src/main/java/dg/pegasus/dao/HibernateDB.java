package dg.pegasus.dao;

import dg.pegasus.User;
import dg.pegasus.UserNotFoundException;
import dg.pegasus.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Component("hibernateDB")
public class HibernateDB implements DataAccess {

  //@Autowired
  private UserRepository userRepository;

  @Override
  public User get(String id) {
    Optional<User> user = userRepository.findById( id );

    if( !user.isPresent() ) {
      throw new UserNotFoundException( id );
    }

    return user.get();
  }

  @Override
  public User save( User o ) {
    return userRepository.save(o);
  }
}
