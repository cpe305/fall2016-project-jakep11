package repositories;

import model.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface that is autowired by the Spring Hibernate Framework to produce Crud operations on the
 * UserRepository.
 * 
 * @author Jake Pickett
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByUsername(String username);
}
