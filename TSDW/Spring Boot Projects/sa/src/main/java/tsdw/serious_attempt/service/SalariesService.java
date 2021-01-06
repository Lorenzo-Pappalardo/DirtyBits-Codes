package tsdw.serious_attempt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsdw.serious_attempt.domain.Salaries;
import tsdw.serious_attempt.repository.SalariesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalariesService {
    @Autowired
    private SalariesRepository salariesRepository;

    public Salaries add(Salaries salary) {
        return salariesRepository.save(salary);
    }

    public Optional<Salaries> get(String role) {
        return salariesRepository.findById(role);
    }

    public List<Salaries> getList() {
        List<Salaries> salaries = new ArrayList<>();
        salariesRepository.findAll().forEach(salaries::add);
        return salaries;
    }

    public void delete(String role) {
        salariesRepository.deleteById(role);
    }
}
