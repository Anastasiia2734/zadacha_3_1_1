package com.example.project2boot.service;

import com.example.project2boot.dao.UserDao;
import com.example.project2boot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        return userDao.read(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.readAll();
    }

    @Override
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public List<User> findUsersName(String name) {
        return userDao.findByName(name);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        userDao.create(user);

    }

}
