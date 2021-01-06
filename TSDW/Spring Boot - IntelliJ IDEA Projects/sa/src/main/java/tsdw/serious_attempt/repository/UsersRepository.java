package tsdw.serious_attempt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsdw.serious_attempt.domain.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, String> {
    Optional<Users> findByName(String name);
}
