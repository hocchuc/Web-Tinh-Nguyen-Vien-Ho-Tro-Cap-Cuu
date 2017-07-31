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
@Component
public class DatabaseLoader implements ApplicationRunner {

    private final userRepository user;
    private final user_typeRepository user_type;
    private final accidentRepository accident;

    private final personal_infoRepository personal_infoRepository;
    private final medical_infoRepository medical_infoRepository;
    private final accident_detailRepository accident_detailRepository;
    private final action_typeRepository action_typeRepository;


        @Autowired
    public DatabaseLoader(userRepository user, user_typeRepository user_type, accidentRepository accident,  personal_infoRepository personal_infoRepository, medical_infoRepository medical_infoRepository, accident_detailRepository accident_detailRepository, action_typeRepository action_typeRepository) {
        this.user = user;
        this.user_type = user_type;
        this.accident = accident;
        this.personal_infoRepository = personal_infoRepository;
        this.medical_infoRepository = medical_infoRepository;
        this.accident_detailRepository = accident_detailRepository;
        this.action_typeRepository = action_typeRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        User_Type admin = new User_Type(null,"admin");
        User_Type volunteer = new User_Type(null,"volunteer");
        User_Type user_normal = new User_Type(null,"user");
        user_type.save(admin);
        user_type.save(volunteer);
        user_type.save(user_normal);

        User user1 = new User(null,"hocanhchuc@gmail.com",user_type.findOne(1l), "123456",null,10.736774, 106.675690);
        user1.setIs_signup_volunteer(true);
        User user2 = new User(null,"trancaotri@gmail.com",user_type.findOne(1l), "123456",null,10.738545, 106.674767);
        user2.setIs_signup_volunteer(true);

        User user3 = new User(null,"diemhang@gmail.com",user_type.findOne(2l), "123456",null,10.741539, 106.677170);
        user3.setIs_signup_volunteer(true);

        User user4 = new User(null,"trantrungduong@gmail.com",user_type.findOne(2l), "123456",null,10.780040, 106.629250);
        user4.setIs_signup_volunteer(true);

        User user5 = new User(null,"truongminhhoang@gmail.com",user_type.findOne(3l), "123456",null,10.737681, 106.677192);
        User user6 = new User(null,"huynhthanhtrung@gmail.com",user_type.findOne(3l), "123456",null,10.741539, 106.677170);
        User user7 = new User(null,"trankimchi@gmail.com",user_type.findOne(2l), "123456",null,10.749258, 106.593523);
        user.save(user1);
        user.save(user2);
        user.save(user3);
        user.save(user4);
        user.save(user5);
        user.save(user6);
        user.save(user7);

            Accident accident1 = new Accident(null,user.findOne(1l),"Gãy lưng",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("10/06/2017 at 10:10:15 AM"),Double.parseDouble("10.737994"),Double.parseDouble("106.679003"),"Pending","87 Ông Ích Khiêm, phường 10, Quận 11, Hồ Chí Minh, Việt Nam",null,true);
        accident.save(accident1);

        Accident accident2 = new Accident(null,user.findOne(1l),"Té xe ",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.781785"),Double.parseDouble("106.643036"),"Pending","541 Âu Cơ, Phú Trung, Tân Phú, Hồ Chí Minh",null,true);
        accident.save(accident2);

        Accident accident3 = new Accident(null,user.findOne(3l),"Chó cắn",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.799555"),Double.parseDouble("106.637219"),"Pending","15-16 Âu Cơ, phường 14, Tân Phú, Hồ Chí Minh",null,true);
        accident.save(accident3);

        //Accident accident4 = new Accident(null,user.findOne(3l),"Té cầu thang",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.783994"),Double.parseDouble("106.636266"),"Pending",null,null,"763 Lũy Bán Bích Hoà Thạnh Tân Phú Hồ Chí Minh",null);
        //accident.save(accident4);
        //
        //Accident accident5 = new Accident(null,user.findOne(2l),"Đau tim",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7123"),Double.parseDouble("106.613"),"Pending",null,null,"Số 454 Âu Cơ Tân Binh Hồ Chí Minh",null);
        //accident.save(accident5);
        //
        //Accident accident6 = new Accident(null,user.findOne(2l),"Té xe",new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a").parse("11/06/2017 at 02:10:15 PM"),Double.parseDouble("10.7142"),Double.parseDouble("106.644"),"Pending",null,null,"Số 433 Âu Cơ Tân Binh Hồ Chí Minh",null);
        //accident.save(accident6);

            Personal_Infomation p1 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F1.PNG?alt=media&token=e485b8b4-3886-421d-90e8-ae461bcce17b","Chúc Anh Học",true,new SimpleDateFormat("dd/MM/yyyy").parse("10/09/1995"),"252956865l","Từ Dũ","0903562332","Phu Tho Hoa","chucanhhoc@gmail.com",user.findOne(1l));
        personal_infoRepository.save(p1);

        Personal_Infomation p2 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F2.jpg?alt=media&token=d9703039-e182-4cbc-91fc-90a069fd5b7c","Trần Cao Trí",true,new SimpleDateFormat("dd/MM/yyyy").parse("20/06/1995"),"252416865l","115","0909999119","Thach Lam","trancaotri@gmail.com",user.findOne(2l));
        personal_infoRepository.save(p2);

        Personal_Infomation p3 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F3.png?alt=media&token=881a65e0-6e88-4b74-828d-f06372fc8705","Lê Diễm Hàng",false,new SimpleDateFormat("dd/MM/yyyy").parse("19/06/1995"),"2524456865l","Chương Dương","0909999113","Hiền Vương","diemhang@gmail.com",user.findOne(3l));
        personal_infoRepository.save(p3);

        Personal_Infomation p4 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F4.jpg?alt=media&token=efb6679f-f8fa-4d34-918a-278fbd51d549","Trần Trung Dương",false,new SimpleDateFormat("dd/MM/yyyy").parse("20/16/1995"),"25244568756","Bệnh Viện Quận 6","0909999213","Cao Lỗ ","trantrungduong@gmail.com",user.findOne(4l));
        personal_infoRepository.save(p4);

        Personal_Infomation p5 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F5.jpg?alt=media&token=9c4ae55c-e74a-4eba-a856-254f9d922298","Trương Minh Hoàng",false,new SimpleDateFormat("dd/MM/yyyy").parse("30/08/1995"),"25244568123","Công ty GPS","0909999222","Bình Long","truongminhhoang@gmail.com",user.findOne(5l));
        personal_infoRepository.save(p5);

        Personal_Infomation p6 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F6.jpg?alt=media&token=af87f071-4e0c-447c-82fe-be840aea9098","Huỳnh Thanh Trung",false,new SimpleDateFormat("dd/MM/yyyy").parse("20/8/1995"),"25244568333","Công ty Technik","0909999654","Long An","huynhthanhtrung@gmail.com",user.findOne(6l));
        personal_infoRepository.save(p6);

        Personal_Infomation p7 = new Personal_Infomation(null,"https://firebasestorage.googleapis.com/v0/b/fir-demo-chat-spring.appspot.com/o/images%2F8.jpg?alt=media&token=43c637d2-deb8-4cce-9c60-71a2edbd0733","Trần Kim Chi",false,new SimpleDateFormat("dd/MM/yyyy").parse("12/2/1995"),"25298568333","Bệnh viện 115","0909999665","Cà Mau","trankimchi@gmail.com",user.findOne(7l));
        personal_infoRepository.save(p7);


        Medical_Info m1 = new Medical_Info(null,personal_infoRepository.findOne(0L),"Panadol",1,"Thuốc đau đầu");
        medical_infoRepository.save(m1);
        Medical_Info m1_2 = new Medical_Info(null,personal_infoRepository.findOne(0L),"Đau đầu",2,"Đau đầu ");
        medical_infoRepository.save(m1_2);

        Medical_Info m2 = new Medical_Info(null,personal_infoRepository.findOne(1L),"bioka",1,"thuoc chua ung thu");
        medical_infoRepository.save(m2);

        Medical_Info m2_2 = new Medical_Info(null,personal_infoRepository.findOne(1L),"Ung Thu",2,"ung thu giai doan 1");
        medical_infoRepository.save(m2_2);

        Action_Type action_type1  =new Action_Type(null,"Join");
        Action_Type action_type2  =new Action_Type(null,"ReportDone");
        Action_Type action_type3  =new Action_Type(null,"ReportFake");
        Action_Type action_type4  =new Action_Type(null,"ReportTrue");

        action_typeRepository.save(action_type1);
        action_typeRepository.save(action_type2);
        action_typeRepository.save(action_type3);
        action_typeRepository.save(action_type4);

        Accident_Detail accident_detail1 = new Accident_Detail(null,new SimpleDateFormat("dd/MM/yyyy").parse("30/08/2017"),user.findOne(1L),accident.findOne(1L),action_typeRepository.findOne(1L));

        Accident_Detail accident_detail2 = new Accident_Detail(null,new SimpleDateFormat("dd/MM/yyyy").parse("30/08/2017"),user.findOne(2L),accident.findOne(1L),action_typeRepository.findOne(1L));
        accident_detailRepository.save(accident_detail1);
        accident_detailRepository.save(accident_detail2);

    }
}
