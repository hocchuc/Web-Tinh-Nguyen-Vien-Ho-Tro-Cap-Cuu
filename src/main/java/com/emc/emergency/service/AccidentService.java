package com.emc.emergency.service;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Accident_Detail;
import com.emc.emergency.data.model.Action_Type;
import com.emc.emergency.data.model.Chat;
import com.emc.emergency.data.model.Image;
import com.emc.emergency.data.model.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emc.emergency.data.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by hocan on 23-May-17.
 */
@Service
public class AccidentService {
    @Autowired
    accidentRepository accidentRepository;
    @Autowired
    userRepository userRepository;
    @Autowired
    chatRepository chatRepository;
    @Autowired
    imageRepository imageRepository;
    @Autowired
    accident_detailRepository accident_detailRepository;
    @Autowired
    action_typeRepository action_typeRepository;

    public boolean CreateAccident(Long id_AC, Long id_user, String description_AC, Date date_AC, Float long_AC, Float lat_AC, String status_AC) {
        User user = null;
        List<Chat> chats = null;
        List<Image> images = null;
        try {
            user = userRepository.findOne(id_user);
            if(user==null) return false;
            /*chats = new ArrayList<>();
            images = new ArrayList<>();
            chats = (List<Chat>) chatRepository.findAll(chatlist);
            images = (List<Image>) imageRepository.findAll(imagelist);*/
      /*      Accident accident = new Accident(null, user, description_AC,  date_AC, long_AC, lat_AC, status_AC, null, null);*/
            //accidentRepository.save(accident);
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

    public void activate(long l) {
        Accident accident = accidentRepository.findOne(l);
        accident.setStatus_AC("Active");
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

        Action_Type action_type = action_typeRepository.findOne(id_action_type);
        User user = userRepository.findOne(id_user);
        Accident accident = accidentRepository.findOne(id_AC);

        if(action_type==null||user==null||accident==null) return false;

        Accident_Detail accident_detail = new Accident_Detail(null, date, user, accident,action_type);
        accident_detailRepository.save(accident_detail);


        return true;
    }
}
