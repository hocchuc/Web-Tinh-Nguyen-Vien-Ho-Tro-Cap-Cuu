package com.emc.emergency.dao;

import com.emc.emergency.model.User;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void delete(User user);

}
