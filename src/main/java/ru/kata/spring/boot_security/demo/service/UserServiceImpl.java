package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    private BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public User findById(Long id){
        return userDao.getById(id);
    }

    @Override
    public User findByEmail(String email){
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user){
        user.setPassword(getEncoder().encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void deleteById(Long id){
        userDao.deleteById(id);
    }

    @Override
    public void updateUser(User user){
        user.setPassword(getEncoder().encode(user.getPassword()));
        userDao.updateUser(user);
    }

}