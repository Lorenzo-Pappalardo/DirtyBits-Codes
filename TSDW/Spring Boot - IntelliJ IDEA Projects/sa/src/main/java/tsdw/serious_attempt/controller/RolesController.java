package tsdw.serious_attempt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsdw.serious_attempt.domain.Roles;
import tsdw.serious_attempt.service.RolesService;
import tsdw.serious_attempt.service.UsersService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/add")
    public ResponseEntity<Roles> add(@Valid @RequestBody Roles role) {
        if (usersService.isRegistered(role.getUser().getEmail()))
            return ResponseEntity.ok(rolesService.add(role));
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Roles>> get() {
        return ResponseEntity.ok(rolesService.getList());
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@Valid @RequestBody Roles role) {
        Optional<Roles> res = rolesService.get(role);
        if (res.isPresent()) {
            rolesService.delete(res.get());
            return ResponseEntity.ok("Role eliminated");
        }
        return ResponseEntity.notFound().build();
    }
}
