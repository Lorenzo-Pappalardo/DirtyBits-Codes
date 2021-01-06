package tsdw.serious_attempt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsdw.serious_attempt.domain.Users;
import tsdw.serious_attempt.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users register(Users newUser) {
        return usersRepository.save(newUser);
    }

    public List<Users> registeredUsers() {
        List<Users> res = new ArrayList<>();
        usersRepository.findAll().forEach(res::add);
        return res;
    }

    public Boolean isRegistered(String email) {
        return usersRepository.existsById(email);
    }

    public Optional<Users> getUser(String email) {
        return usersRepository.findById(email);
    }

    public Optional<Users> getUserByName(String name) {
        return usersRepository.findByName(name);
    }

    public void delete(String email) {
        usersRepository.deleteById(email);
    }
}
