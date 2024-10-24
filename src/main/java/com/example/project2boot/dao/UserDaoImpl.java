package com.example.project2boot.dao;

import com.example.project2boot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public User read(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> readAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        try {
            entityManager.merge(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User with ID " + user.getId() + " not found for update");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        User user = read(id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public List<User> findByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.firstName LIKE :name", User.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}
