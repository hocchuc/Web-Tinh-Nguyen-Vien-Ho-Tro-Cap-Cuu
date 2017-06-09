package com.emc.emergency.core;

import com.emc.emergency.data.model.*;
import com.emc.emergency.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        User user1 = new User(null,"hocanhchuc@gmail.com", null,null,user_type.findOne(2l),null,"123",10.712746, 106.614751);
        User user2 = new User(null,"trancaotri@gmail.com", null,null,user_type.findOne(2l),null,"123",10.777437, 106.630484);
        User user3 = new User(null,"nguyenhuunghia@gmail.com", null,null,user_type.findOne(12l),null,"123",10.780040, 106.629250);
        user.save(user1);
        user.save(user2);
        user.save(user3);

        Personal_Infomation p1 = new Personal_Infomation(user.findOne(2L),"Chúc Anh Học",true,new SimpleDateFormat("dd/MM/yyyy").parse("10/09/1995"),252956865l,"Từ Dữ","0909999999","Phu Tho Hoa","chucanhhoc@gmail.com");
        personal_infoRepository.save(p1);
        Medical_Info m1 = new Medical_Info(null,personal_infoRepository.findOne(2l),"panadol",1,"thuoc dau dau");
        Medical_Info m1_2 = new Medical_Info(null,personal_infoRepository.findOne(2l),"Dau dau",2,"dau dau");
        medical_infoRepository.save(m1);
        medical_infoRepository.save(m1_2);

        Personal_Infomation p2 = new Personal_Infomation(user.findOne(12L),"Trần Cao Trí",true,new SimpleDateFormat("dd/MM/yyyy").parse("20/06/1995"),252416865l,"115","0909999119","Thach Lam","trancaotri@gmail.com");
        personal_infoRepository.save(p2);
        Medical_Info m2 = new Medical_Info(null,personal_infoRepository.findOne(12l),"bioka",1,"thuoc chua ung thu");
        Medical_Info m2_2 = new Medical_Info(null,personal_infoRepository.findOne(12l),"Ung Thu",2,"ung thu giai doan 1");

        medical_infoRepository.save(m2);
        medical_infoRepository.save(m2_2);


        Accident accident1 = new Accident(null,"Gãy chân ở Tân Phú",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("10/06/2017 at 10:10:15 AM"),Float.parseFloat("10.7973"),Float.parseFloat("106.649"),"active","Số 2 Lô L chung cư Bàu cát 3 Tân Phú Hồ Chí Minh");
        accident.save(accident1);
        Accident accident2 = new Accident(null,"Té xe vì bị bò đá",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7153"),Float.parseFloat("106.638"),"active","Số 3 Nguyễn Thị Minh Khai Quận 1 Hồ Chí Minh");
        accident.save(accident2);
        Accident accident3 = new Accident(null,"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7143"),Float.parseFloat("106.645"),"active","Số 123 Âu Cơ Tân Binh Hồ Chí Minh");
        accident.save(accident3);
        Accident accident4 = new Accident(null,"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7133"),Float.parseFloat("106.690"),"active","Số 4353 Âu Cơ Tân Binh Hồ Chí Minh");
        accident.save(accident4);
        Accident accident5 = new Accident(null,user.findOne(12L),"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7123"),Float.parseFloat("106.613"),"active",null,null,"Số 454 Âu Cơ Tân Binh Hồ Chí Minh");
        accident.save(accident5);
        Accident accident6 = new Accident(null,user.findOne(12L),"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Float.parseFloat("10.7142"),Float.parseFloat("106.644"),"active",null,null,"Số 433 Âu Cơ Tân Binh Hồ Chí Minh");
        accident.save(accident6);

        Chat chat1 = new Chat(null,accident.findOne(2l),user.findOne(2L),"Chuyện gì thế ?",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:20 PM"));
        chat.save(chat1);

//        ArrayList<Medical_Info> list_mi1 = new ArrayList<Medical_Info>();
//        list_mi1.add(m1);
//        list_mi1.add(m1_2);





    }
}
