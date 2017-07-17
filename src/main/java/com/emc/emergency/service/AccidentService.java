package com.emc.emergency.service;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Accident_Detail;
import com.emc.emergency.data.model.Action_Type;
import com.emc.emergency.data.model.User;
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
    @Autowired
    accidentRepository accidentRepository;
    @Autowired
    userRepository userRepository;

    @Autowired
    accident_detailRepository accident_detailRepository;
    @Autowired
    action_typeRepository action_typeRepository;

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
        Iterable<Accident_Detail> accidentDetails = accident_detailRepository.findAll() ;
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
            if(accident_detail.getAction_type().getName_action().equals("Join")){
                accident.setJoined(accident.getJoined() + 1);
                accidentRepository.save(accident);
            }
            if (accident_detail.getAction_type().getName_action().equals("ReportFake")){
                accident.setIs_reported_fake(true);
                accidentRepository.save(accident);

            }
            if (accident_detail.getAction_type().getName_action().equals("ReportTrue")){
                accident.setIs_reported_fake(false);
                accidentRepository.save(accident);

            }
            if (accident_detail.getAction_type().getName_action().equals("SetDone")){
                accident.setStatus_AC("Done");
                accidentRepository.save(accident);

            }

        }

        accident_detailRepository.save(accident_detail);


        return true;
    }



}
