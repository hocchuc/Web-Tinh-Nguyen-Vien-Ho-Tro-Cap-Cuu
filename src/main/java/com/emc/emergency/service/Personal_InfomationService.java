package com.emc.emergency.service;
import com.emc.emergency.data.model.Medical_Info;
import com.emc.emergency.data.model.Personal_Infomation;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hocan on 23-May-17.
 */
@Service

public class Personal_InfomationService {
    @Autowired
    personal_infoRepository personal_infoRepository;

    public boolean create_personal_Info(Long id_PI, User id_user, String name_PI, Boolean sex__PI, Date birthday, Integer personal_id, String work_location, Float long_PI, Float lat_PI, Integer phone_PI, String address_PI, String email_PI, List<Medical_Info> medical_Info) {

        try {
            Personal_Infomation personal_infomation = new Personal_Infomation(null, id_user,  name_PI,  sex__PI,  birthday,  personal_id,  work_location,  long_PI,  lat_PI,  phone_PI,  address_PI,  email_PI,  medical_Info) ;
            personal_infoRepository.save(personal_infomation);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
