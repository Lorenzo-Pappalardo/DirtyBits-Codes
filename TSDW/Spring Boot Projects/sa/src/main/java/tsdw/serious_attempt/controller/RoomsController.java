package tsdw.serious_attempt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsdw.serious_attempt.domain.Rooms;
import tsdw.serious_attempt.domain.Users;
import tsdw.serious_attempt.service.RoomsService;
import tsdw.serious_attempt.service.UsersService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {
    @Autowired
    private RoomsService roomsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/getList")
    public ResponseEntity<List<Rooms>> getList() {
        return ResponseEntity.ok(roomsService.getList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Rooms> getList(@Valid @PathVariable Integer id) {
        Optional<Rooms> room = roomsService.get(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add/{usersNames}")
    public ResponseEntity<Rooms> add(@Valid @PathVariable String[] usersNames) {
        List<Users> usersList = new ArrayList<>();
        for (String userName : usersNames) {
            Optional<Users> user = usersService.getUserByName(userName);
            if (!user.isPresent()) return ResponseEntity.badRequest().build();
            usersList.add(user.get());
        }
        return ResponseEntity.ok(roomsService.add(new Rooms(usersList)));
    }

    @PutMapping("/edit/{id}-{usersNames}")
    public ResponseEntity<Rooms> edit(@Valid @PathVariable Integer id, @Valid @PathVariable String[] usersNames) {
        System.out.println(id);
        if (roomsService.get(id).isPresent()) {
            roomsService.delete(id);
            return add(usersNames);
        }
        return ResponseEntity.notFound().build();
    }
}
