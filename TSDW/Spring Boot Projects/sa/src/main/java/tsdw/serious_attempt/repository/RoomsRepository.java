package tsdw.serious_attempt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsdw.serious_attempt.domain.Rooms;

@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Integer> {
}
