package repositories;

import model.Triathlon;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TriathlonRepository extends CrudRepository<Triathlon, Long> {

  List<Triathlon> findByName(String name);
}
