package com.sapo.edu.demo.dao;

import com.sapo.edu.demo.model.User;

import java.util.List;
import java.util.Optional;


public interface IUserDao {
  Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    List<User> getAllUser();
    void save(User user);
    User findLastUser();

}