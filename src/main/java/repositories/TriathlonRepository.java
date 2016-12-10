package repositories;

import model.Triathlon;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface that is autowired by the Spring Hibernate Framework to produce Crud operations on the
 * TriathlonRepository.
 * 
 * @author Jake Pickett
 *
 */
public interface TriathlonRepository extends CrudRepository<Triathlon, Long> {

  List<Triathlon> findByName(String name);
}
