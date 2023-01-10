package ru.kata.spring.boot_security.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    public CommandLineRunnerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        if (roleService.findByName("ROLE_ADMIN") == null) {
            roleService.addRoleAtStartup(1L, "ROLE_ADMIN");
        }

        if (roleService.findByName("ROLE_USER") == null) {
            roleService.addRoleAtStartup(2L, "ROLE_USER");
        }

        User admin = userService.getByEmail("admin@email.ru");
        if (admin == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_ADMIN"));
            roles.add(roleService.findByName("ROLE_USER"));
            admin = new User(
                    1L,
                    "admin",
                    "admin",
                    (byte) 35,
                    "admin@email.ru",
                    "$2a$12$im/KwQaC6Ia.Dj9BiFYmxeSh1ClnDt8YAGnHYqieTa.8Jn7dhp4Kq", //admin
                    roles
            );
            userService.addUserAtStartup(admin);
        }

        User user = userService.getByEmail("user@email.ru");
        if (user == null) {
            user = new User(
                    2L,
                    "user",
                    "user",
                    (byte) 30,
                    "user@email.ru",
                    "$2a$12$QKGsxf11dkQ9jthWA9S64ufdCHRGfeL0gINxSEYmAnujjoJH.DAPG", //user
                    Collections.singleton(roleService.findByName("ROLE_USER"))
            );
            userService.addUserAtStartup(user);
        }
    }
}
