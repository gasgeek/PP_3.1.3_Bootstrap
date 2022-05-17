package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getById(int id);

    Role getRoleByName(String name);

    HashSet<Role> getSetOfRoles(String[] roleNames);

    void addRole(Role role);
}