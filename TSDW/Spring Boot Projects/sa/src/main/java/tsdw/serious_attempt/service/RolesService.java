package tsdw.serious_attempt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsdw.serious_attempt.domain.Roles;
import tsdw.serious_attempt.repository.RolesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public Roles add(Roles role) {
        return rolesRepository.save(role);
    }

    public List<Roles> getList() {
        List<Roles> tmp = new ArrayList<>();
        rolesRepository.findAll().forEach(tmp::add);
        return tmp;
    }

    public Optional<Roles> get(Roles role) {
        return rolesRepository.findByRoleAndUser(role.getRole(), role.getUser());
    }

    public boolean exist(String role) {
        return rolesRepository.existsByRole(role);
    }

    public void delete(Roles role) {
        rolesRepository.delete(role);
    }
}
