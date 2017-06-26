package com.emc.emergency.core;

import com.emc.emergency.data.model.*;
import com.emc.emergency.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by hocan on 23-May-17.
 */
//@Component
public class DatabaseLoader implements ApplicationRunner {

    private final userRepository user;
    private final user_typeRepository user_type;
    private final accidentRepository accident;
    private final chatRepository chat;
    private final imageRepository imageRepository;
    private final personal_infoRepository personal_infoRepository;
    private final medical_infoRepository medical_infoRepository;

    @Autowired
    public DatabaseLoader(userRepository user, user_typeRepository user_type, accidentRepository accident, chatRepository chat, imageRepository imageRepository, personal_infoRepository personal_infoRepository, medical_infoRepository medical_infoRepository) {
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

        User user1 = new User(null,"hocanhchuc@gmail.com",user_type.findOne(2l), "123456",null,10.712746, 106.614751);
        User user2 = new User(null,"trancaotri@gmail.com",user_type.findOne(1l), "123456",null,10.777437, 106.630484);
        User user3 = new User(null,"nguyenhuunghia@gmail.com",user_type.findOne(3l), "123",null,10.780040, 106.629250);
        user.save(user1);
        user.save(user2);
        user.save(user3);

        Accident accident1 = new Accident(null,user.findOne(1l),"Gãy lưng",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("10/06/2017 at 10:10:15 AM"),Double.parseDouble("10.7973"),Double.parseDouble("106.649"),"active",null,null,"Số 2 Lô L chung cư Bàu cát 3 Tân Phú Hồ Chí Minh",null);
        accident.save(accident1);
        Accident accident2 = new Accident(null,user.findOne(1l),"Té xe ",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7153"),Double.parseDouble("106.638"),"active",null,null,"Số 3 Nguyễn Thị Minh Khai Quận 1 Hồ Chí Minh",null);
        accident.save(accident2);
        Accident accident3 = new Accident(null,user.findOne(3l),"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7143"),Double.parseDouble("106.645"),"active",null,null,"Số 123 Âu Cơ Tân Binh Hồ Chí Minh",null);
        accident.save(accident3);
        Accident accident4 = new Accident(null,user.findOne(3l),"Té cầu thang",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7133"),Double.parseDouble("106.690"),"active",null,null,"Số 4353 Âu Cơ Tân Binh Hồ Chí Minh",null);
        accident.save(accident4);
        Accident accident5 = new Accident(null,user.findOne(2l),"Đau tim",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7123"),Double.parseDouble("106.613"),"active",null,null,"Số 454 Âu Cơ Tân Binh Hồ Chí Minh",null);
        accident.save(accident5);
        Accident accident6 = new Accident(null,user.findOne(2l),"Té xe",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7142"),Double.parseDouble("106.644"),"active",null,null,"Số 433 Âu Cơ Tân Binh Hồ Chí Minh",null);
        accident.save(accident6);

        Personal_Infomation p1 = new Personal_Infomation(null,null,"Chúc Anh Học",true,new SimpleDateFormat("dd/MM/yyyy").parse("10/09/1995"),"252956865l","Từ Dữ","0909999999","Phu Tho Hoa","chucanhhoc@gmail.com",user.findOne(1l));
        personal_infoRepository.save(p1);
        Personal_Infomation p2 = new Personal_Infomation(null,null,"Trần Cao Trí",true,new SimpleDateFormat("dd/MM/yyyy").parse("20/06/1995"),"252416865l","115","0909999119","Thach Lam","trancaotri@gmail.com",user.findOne(2l));
        personal_infoRepository.save(p2);

        Medical_Info m1 = new Medical_Info(null,personal_infoRepository.findOne(0L),"panadol",1,"thuoc dau dau");
        medical_infoRepository.save(m1);
        Medical_Info m1_2 = new Medical_Info(null,personal_infoRepository.findOne(0L),"Dau dau",2,"dau dau");
        medical_infoRepository.save(m1_2);
        Medical_Info m2 = new Medical_Info(null,personal_infoRepository.findOne(1L),"bioka",1,"thuoc chua ung thu");
        medical_infoRepository.save(m2);
        Medical_Info m2_2 = new Medical_Info(null,personal_infoRepository.findOne(1L),"Ung Thu",2,"ung thu giai doan 1");
        medical_infoRepository.save(m2_2);

        Chat chat1 = new Chat(null,accident.findOne(1l),user.findOne(1L),"Chuyện gì thế ?",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:20 PM"));
        chat.save(chat1);
        Chat chat2 = new Chat(null,accident.findOne(2l),user.findOne(2L),"Chúng tôi sẽ đến trong thời gian sớm nhất?",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:11:20 PM"));
        chat.save(chat2);

//        ArrayList<Medical_Info> list_mi1 = new ArrayList<Medical_Info>();
//        list_mi1.add(m1);
//        list_mi1.add(m1_2);
    }
}
