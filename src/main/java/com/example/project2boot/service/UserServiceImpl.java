package com.example.project2boot.service;

import com.example.project2boot.dao.UserDao;
import com.example.project2boot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userDao.create(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.read(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.readAll();
    }

    @Override
    @Transactional
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        try {
            userDao.update(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("error updating the user");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        try {
            userDao.delete(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public List<User> findUsersName(String name) {
        return userDao.findByName(name);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.create(user);
    }
}
