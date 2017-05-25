package com.emc.emergency.service;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Chat;
import com.emc.emergency.data.model.Image;
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
    chatRepository chatRepository;
    @Autowired
    imageRepository imageRepository;
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
            Accident accident = new Accident(null, user, description_AC,  date_AC, long_AC, lat_AC, status_AC, null, null);
            accidentRepository.save(accident);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
