package org.example.REST_CRUD.service;

import org.example.REST_CRUD.model.User;
import org.example.REST_CRUD.repository.UserRepository;
import org.example.REST_CRUD.repository.implementations.UserRepositoryImpl;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User s) {
        return userRepository.update(s);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
