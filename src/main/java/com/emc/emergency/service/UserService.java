package com.emc.emergency.service;

import com.emc.emergency.data.model.User;
import com.emc.emergency.data.model.User_Type;
import com.emc.emergency.data.repository.*;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.data.repository.user_typeRepository;
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
    @Autowired
    user_typeRepository user_typeRepository;
    @Autowired
     personal_infoRepository personal_infoRepository;
    @Autowired
    chatRepository chatRepository;
    @Autowired
    accidentRepository accidentRepository;


    public Boolean Login(String username,String password ) {
        List<User> userList= userRepository.findByUsernameAndPassword(username,password);
        if(userList.isEmpty()) return false;
        return true;
    }

    public Boolean Register(String username,String password ) {
        List<User> userList= userRepository.findByUsername(username);
        if(userList.isEmpty())
        {   User_Type user_type = user_typeRepository.findOne(2l);
            User user = new User(null,username, token, user_type,password);
            userRepository.save(user);
            return true;

        }
        return false;
    }


}
