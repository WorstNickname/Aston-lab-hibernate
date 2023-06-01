package ru.aston.service;

import lombok.RequiredArgsConstructor;
import ru.aston.entity.User;
import ru.aston.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean save(User user) {
        var mayBeUser = userRepository.findByEmail(user.getEmail());

        if (mayBeUser.isEmpty()) {
            userRepository.save(user);
            return true;
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    public boolean delete(Long id) {
        var mayBeUser = userRepository.findById(id);
        mayBeUser.ifPresent(userRepository::delete);
        return mayBeUser.isPresent();
    }

}
