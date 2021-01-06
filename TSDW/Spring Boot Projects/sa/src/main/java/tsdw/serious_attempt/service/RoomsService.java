package tsdw.serious_attempt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsdw.serious_attempt.domain.Rooms;
import tsdw.serious_attempt.repository.RoomsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;

    public Rooms add(Rooms room) {
        return roomsRepository.save(room);
    }

    public Optional<Rooms> get(Integer id) {
        return roomsRepository.findById(id);
    }

    public List<Rooms> getList() {
        List<Rooms> tmp = new ArrayList<>();
        roomsRepository.findAll().forEach(tmp::add);
        return tmp;
    }

    public Rooms edit(Rooms room) {
        return roomsRepository.save(room);
    }

    public void delete(Integer id) {
        roomsRepository.deleteById(id);
    }
}
