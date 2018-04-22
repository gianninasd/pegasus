package dg.pegasus;

import org.springframework.data.repository.CrudRepository;

/**
 * DAO for User domain object
 * @author gianninasd
 */
public interface UserRepository extends CrudRepository<User, String> {

}