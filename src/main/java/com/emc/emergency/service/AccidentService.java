package com.emc.emergency.service;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Accident_Detail;
import com.emc.emergency.data.model.Action_Type;
import com.emc.emergency.data.model.User;
import com.emc.emergency.web.controller.AccidentController;
import com.emc.emergency.xmpp.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emc.emergency.data.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hocan on 23-May-17.
 */
@Service
public class AccidentService {
    private static final Logger logger = LoggerFactory.getLogger(AccidentService.class);

    @Autowired
    accidentRepository accidentRepository;
    @Autowired
    userRepository userRepository;

    @Autowired
    accident_detailRepository accident_detailRepository;
    @Autowired
    action_typeRepository action_typeRepository;

    @Autowired
    FCMService fcmService;
     MessageSender messageSender = new MessageSender();

    public boolean CreateAccident(Long id_AC, Long id_user, String description_AC, Date date_AC, Float long_AC, Float lat_AC, String status_AC) {
        User user = null;

        try {
            user = userRepository.findOne(id_user);
            if(user==null) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Accident> GetAccident() {
        List<Accident> accidents =  accidentRepository.findAll();
        return accidents;
    }

    public void removeOne(long l) {
        accidentRepository.delete(l);
    }

    public void activate(long id_AC, long id_admin) {
        Accident accident = accidentRepository.findOne(id_AC);
        accident.setStatus_AC("Active");
        accident.setId_admin_active(userRepository.findOne(id_admin));
        accidentRepository.save(accident);
    }

    public void setdone(long l) {
        Accident accident = accidentRepository.findOne(l);
        accident.setStatus_AC("Done");
        accidentRepository.save(accident);
    }

    public List<Accident> GetAccidentWithStatus(String status) {
        List<Accident> accidentList = accidentRepository.findAll();
        List<Accident> returnList = new ArrayList<Accident>();
        for(Accident accident : accidentList) {
            if(accident.getStatus_AC().equals(status)) returnList.add(accident);
        }

        return returnList;
    }

    public boolean CreateAccidentDetail(Long id_user,Long id_AC,Long id_action_type, Date date){
        List<Accident_Detail> accidentDetails = accident_detailRepository.findAll() ;
        Action_Type action_type = action_typeRepository.findOne(id_action_type);
        User user = userRepository.findOne(id_user);
        Accident accident = accidentRepository.findOne(id_AC);

        if(action_type==null||user==null||accident==null) return false;
        Accident_Detail accident_detail = new Accident_Detail(null, date, user, accident,action_type);

        Boolean is_duplicate = false;
        for (Accident_Detail accidentdel : accidentDetails){
            // compare 3 long - -
            if(accident_detail.getId_user().getId_user().equals(accidentdel.getId_user().getId_user())&&
                accident_detail.getAction_type().getId_action().equals(accidentdel.getAction_type().getId_action())&&accident_detail.getId_AC().getId_AC().equals(accidentdel.getId_AC().getId_AC()))
            {
                is_duplicate=true;
                break;
            }
        }

        if(is_duplicate) {
            return false;
        }
        else {
               List<User> UserFilterd = new ArrayList<>();
               for (Accident_Detail ad : accidentDetails) {
                 if (ad.getId_AC().equals(accidentRepository.findOne((accident_detail.getId_AC().getId_AC())))) {    if(accident_detail.getId_user().getLat_PI()!=null&&accident_detail.getId_user().getLong_PI()!=null)
                   UserFilterd.add(accident_detail.getId_user());
                   logger.info(accident_detail.getId_user().toString());
                 }
               }

            if(accident_detail.getAction_type().getName_action().equals("Join")){
                accident.setJoined(accident.getJoined() + 1);
                accidentRepository.save(accident);
                MessageSender messageSender = new MessageSender();
                messageSender.SendNotiToOneUser(accident.getId_user(),fcmService,accident_detail.getId_user().getPersonal_Infomation().getName_PI()+" đã tham gia hỗ trợ bạn","Đang có người đến hỗ trợ bạn");
            }
            if (accident_detail.getAction_type().getName_action().equals("ReportFake")){
                accident.setIs_reported_fake(true);
                accidentRepository.save(accident);
                MessageSender messageSender = new MessageSender();
                 messageSender.SendNotiToAllUser(UserFilterd,fcmService,accident_detail.getId_user().getPersonal_Infomation().getName_PI()+" đã báo "+accident.getAddress()+"  là tai nạn giả","Tai nạn giả");

            }
            if (accident_detail.getAction_type().getName_action().equals("ReportDone")){
                accident.setIs_report_done(false);
                accidentRepository.save(accident);
                MessageSender messageSender = new MessageSender();
                 messageSender.SendNotiToAllUser(UserFilterd,fcmService,accident_detail.getId_user().getPersonal_Infomation().getName_PI()+" đã báo "+accident.getAddress()+"  kết thúc tai nạn","Tai nạn kết thúc");

            }
            if (accident_detail.getAction_type().getName_action().equals("SetDone")){
                accident.setStatus_AC("Done");
                accidentRepository.save(accident);
                MessageSender messageSender = new MessageSender();
                messageSender.SendNotiToAllUser(UserFilterd,fcmService,accident_detail.getId_user().getPersonal_Infomation().getName_PI()+" đã báo kết thúc tai nạn "+accident.getAddress(),"Tai nạn kết thúc");

            }

        }

        accident_detailRepository.save(accident_detail);


        return true;
    }



}
