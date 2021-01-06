package tsdw.serious_attempt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsdw.serious_attempt.domain.Users;
import tsdw.serious_attempt.service.UsersService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@Valid @RequestBody Users user) throws URISyntaxException {
        if (usersService.isRegistered(user.getEmail())) return ResponseEntity.badRequest().build();
        return ResponseEntity.created(new URI("/api/users/" + user.getEmail())).body(usersService.register(user));
    }

    @GetMapping
    public ResponseEntity<List<Users>> registeredUsers() {
        return ResponseEntity.ok(usersService.registeredUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Users> getUser(@Valid @PathVariable String email) {
        Optional<Users> res = usersService.getUser(email);
        if (res.isPresent())
            return ResponseEntity.ok(res.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@Valid @RequestBody Users user) {
        Optional<Users> res = usersService.getUser(user.getEmail());
        if (res.isPresent()) {
            return ResponseEntity.ok(usersService.register(user));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@Valid @RequestParam String email, @Valid @RequestParam String password) {
        Optional<Users> user = usersService.getUser(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            usersService.delete(email);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.badRequest().body("User not found or wrong password given");
    }
}
