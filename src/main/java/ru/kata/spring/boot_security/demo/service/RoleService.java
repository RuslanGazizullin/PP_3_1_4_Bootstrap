package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    void addRoleAtStartup(Long id, String name);

    Role findByName(String name);

    List<Role> findAll();
}
