package com.example.project2boot.dao;


import com.example.project2boot.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    User read(Long id);

    List<User> readAll();

    void update(User user);

    void delete(Long id);

    public List<User> findByName(String name);

}
