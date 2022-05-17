package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void updateUser(User user);

    List<User> findAll();

    User getById(Long id);

    User getByEmail(String email);

    void save(User user);

    void deleteById(Long id);
}