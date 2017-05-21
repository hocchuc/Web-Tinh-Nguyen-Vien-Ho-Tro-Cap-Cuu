package com.emc.emergency.service;

import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocan on 22-May-17.
 */
@Service
public class UserService {
    @Autowired
    userRepository userRepository;

    public Boolean Login(String username,String password ) {
        List<User> userList= userRepository.findByUsernameAndPassword(username,password);
        if(userList.isEmpty()) return false;
        return true;
    }
}
