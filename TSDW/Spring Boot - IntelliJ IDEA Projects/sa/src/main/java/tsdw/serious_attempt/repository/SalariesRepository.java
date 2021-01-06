package tsdw.serious_attempt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsdw.serious_attempt.domain.Salaries;

@Repository
public interface SalariesRepository extends CrudRepository<Salaries, String> {
}
