package tsdw.serious_attempt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsdw.serious_attempt.domain.Salaries;
import tsdw.serious_attempt.service.RolesService;
import tsdw.serious_attempt.service.SalariesService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salaries")
public class SalariesController {
    @Autowired
    private SalariesService salariesService;

    @Autowired
    private RolesService rolesService;

    @PostMapping("/add")
    public ResponseEntity<Salaries> add(@Valid @RequestBody Salaries salary) {
        if (rolesService.exist(salary.getRole()))
            return ResponseEntity.ok(salariesService.add(salary));
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Salaries>> get() {
        return ResponseEntity.ok(salariesService.getList());
    }

    @PostMapping("/delete/{role}")
    public ResponseEntity<String> delete(@Valid @PathVariable String role) {
        Optional<Salaries> tmp = salariesService.get(role);
        if (tmp.isPresent()) {
            salariesService.delete(role);
            return ResponseEntity.ok("Salary eliminated");
        }
        return ResponseEntity.notFound().build();
    }
}
