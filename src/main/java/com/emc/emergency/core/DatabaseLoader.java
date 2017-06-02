package com.emc.emergency.core;

import com.emc.emergency.data.model.*;
import com.emc.emergency.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hocan on 23-May-17.
 */
@Component
public class DatabaseLoader implements ApplicationRunner {

    private final userRepository user;
    private final user_typeRepository user_type;
    private final accidentRepository accident;
    private final chatRepository chat;
    private final imageRepository imageRepository;
    private final personal_infoRepository personal_infoRepository;
    private final medical_infoRepository medical_infoRepository;

    @Autowired
    public DatabaseLoader(userRepository user, user_typeRepository user_type, accidentRepository accident, chatRepository chat, com.emc.emergency.data.repository.imageRepository imageRepository, com.emc.emergency.data.repository.personal_infoRepository personal_infoRepository, com.emc.emergency.data.repository.medical_infoRepository medical_infoRepository) {
        this.user = user;
        this.user_type = user_type;
        this.accident = accident;
        this.chat = chat;
        this.imageRepository = imageRepository;
        this.personal_infoRepository = personal_infoRepository;
        this.medical_infoRepository = medical_infoRepository;
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

        Accident accident1 = new Accident(null,"Gãy chân ở Tân Phú",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("10/06/2017 at 10:10:15 AM"),Float.parseFloat("10.7973"),Float.parseFloat("106.649"),"active","Số 2 Lô L chung cư Bàu cát 3 Tân Phú Hồ Chí Minh");
        accident.save(accident1);
        Accident accident2 = new Accident(null,"Té xe vì bị bò đá",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7153"),Float.parseFloat("106.638"),"active","Số 3 Nguyễn Thị Minh Khai Quận 1 Hồ Chí Minh");
        accident.save(accident2);
        Accident accident3 = new Accident(null,"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7153"),Float.parseFloat("106.638"),"active","Số 123 Âu Cơ Tân Binh Hồ Chí Minh");
        accident.save(accident3);

        Chat chat1 = new Chat(null,accident.findOne(2l),user.findOne(2L),"Chuyện gì thế ?",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:20 PM"));
        chat.save(chat1);



    }
}
