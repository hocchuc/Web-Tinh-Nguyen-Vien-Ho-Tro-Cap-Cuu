package com.emc.emergency.core;

import com.emc.emergency.data.model.*;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.data.repository.user_typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocan on 23-May-17.
 */
public class DatabaseLoader implements ApplicationRunner {

    private final userRepository user;

    private final user_typeRepository user_type;

    @Autowired
    public DatabaseLoader(userRepository user, user_typeRepository user_type) {
        this.user = user;
        this.user_type = user_type;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        User_Type admin = new User_Type(null,"admin");
        User_Type volunteer = new User_Type(null,"volunteer");
        User_Type user_normal = new User_Type(null,"user");
        user_type.save(admin);
        user_type.save(volunteer);
        user_type.save(user_normal);

        User user1 = new User(null,"hocanhchuc@gmail.com",user_type.findOne(1l),"123");
        User user2 = new User(null,"trancaotri@gmail.com",user_type.findOne(1l),"123");
        User user3 = new User(null,"nguyenhuunghia@gmail.com",user_type.findOne(2l),"123");
        user.save(user1);
        user.save(user2);
        user.save(user3);
        user2.setId_user_type(volunteer);
        user.save(user2);




    }
}
