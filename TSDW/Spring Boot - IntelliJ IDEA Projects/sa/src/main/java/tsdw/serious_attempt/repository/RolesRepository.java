package tsdw.serious_attempt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsdw.serious_attempt.domain.Roles;
import tsdw.serious_attempt.domain.Users;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface RolesRepository extends CrudRepository<Roles, Integer> {
    Optional<Roles> findByRoleAndUser(@NotBlank String role, Users user);

    boolean existsByRole(String role);
}
