package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(Long id, Principal principal);

    void updateUser(Long id, User user);

    User getById(Long id);

    User getByEmail(String email);

    void addUserAtStartup(User user);
}
