package com.emc.emergency.dao;

import com.emc.emergency.model.User;
import com.emc.emergency.model.User_Type;

import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public interface User_TypeDao {
    List<User_Type> findAll();
    User_Type findById(Long id);
    void save(User_Type user_type);
    void delete(User_Type user_type);
}
