package com.emc.emergency.web.controller;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Accident_Detail;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.data.repository.accident_detailRepository;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.service.AccidentService;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.service.UserService;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.MessageSender;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by hocan on 01-Jul-17.
 */
@Controller
public class AccidentController {
    private static final Logger logger = LoggerFactory.getLogger(AccidentController.class);
    @Autowired
    NoticeController noticeController;
    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;
    @Autowired
    accident_detailRepository accident_detailRepository;
    @Autowired
    userRepository userRepository;
    @Autowired
    AccidentService accidentService;
    @Autowired
    accidentRepository accidentRepo;

    @Autowired FCMService fcmService;

    @RequestMapping(value = "/accidents")
    public String AccidentIndex(Model model ) {

        Long id_admin = (Long) session.getAttribute("id_admin");
        if(id_admin==null) return "mainpage/login";

        List<Accident> accidents = accidentService.GetAccidentWithStatus("Active");
        List<Accident> accidents2 = accidentService.GetAccidentWithStatus("Pending");
        accidents.addAll(accidents2);

        model.addAttribute("accidents",accidents);
        return "mainpage/accident_index";
    }



    @RequestMapping(value="accident/remove/{accidentID}", method= RequestMethod.POST)
    public String remove(
            @PathVariable("accidentID") String id, Model model
    ) {
        Long id_admin = (Long) session.getAttribute("id_admin");
        if(id_admin==null) return "mainpage/login";
        List<Accident_Detail> accidentDetails = accident_detailRepository.findAll();
        for(Accident_Detail accident_detail : accidentDetails) {
          if(accident_detail.getId_AC().getId_AC()==Long.parseLong(id))accident_detailRepository.delete(accident_detail.getId_AC_detail());
        }
        accidentService.removeOne((Long.parseLong(id)));
        List<Accident> accidents = accidentService.GetAccidentWithStatus("Active");
        List<Accident> accidents2 = accidentService.GetAccidentWithStatus("Pending");
        accidents.addAll(accidents2);
        model.addAttribute("accidents",accidents);

        return "mainpage/accident_index";
    }

    @RequestMapping(value="accident/activate/{accidentID}", method= RequestMethod.POST)
    public String activate(
            @PathVariable("accidentID") String id, Model model
    ) { //Đổi thuộc tính active
        Long id_admin = (Long) session.getAttribute("id_admin");
        if(id_admin==null) return "mainpage/login";
        // Gửi noti cho user
        accidentService.activate(Long.parseLong(id),id_admin);
        MessageSender messageSender = new MessageSender();
        messageSender.sendAccident(accidentRepo.findOne(Long.parseLong(id)),userService.findAll(),fcmService, id,noticeController);

        //Chuẩn bị đối tượng cho Spring MVC
        List<Accident> accidents = accidentService.GetAccidentWithStatus("Active");
        List<Accident> accidents2 = accidentService.GetAccidentWithStatus("Pending");
        accidents.addAll(accidents2);
        model.addAttribute("accidents",accidents);


        return "mainpage/accident_index";
    }

    @RequestMapping(value="accident/setdone/{accidentID}", method= RequestMethod.POST)
    public String setdone(
        @PathVariable("accidentID") String id, Model model
    ) { //Đổi thuộc tính active thành done
        Long id_admin = (Long) session.getAttribute("id_admin");
        if(id_admin==null) return "mainpage/login";
        accidentService.setdone(Long.parseLong(id));

        MessageSender messageSender = new MessageSender();
        messageSender.sendAccidentDone(accidentRepo.findOne(Long.parseLong(id)),userService.findAll(),fcmService,noticeController);
        //Chuẩn bị đối tượng cho Spring MVC
        List<Accident> accidents = accidentService.GetAccidentWithStatus("Active");
        List<Accident> accidents2 = accidentService.GetAccidentWithStatus("Pending");
        accidents.addAll(accidents2);
        model.addAttribute("accidents",accidents);


        return "mainpage/accident_index";
    }

  @RequestMapping(value = "accident/{accidentID}", method = RequestMethod.GET)
  public String Accident_Detail(
      @PathVariable("accidentID") String id, Model model
  ) {
    List<Accident_Detail> accidentDetails = accident_detailRepository.findAll();
    List<User> UserFilterd = new ArrayList<>();
    for (Accident_Detail accident_detail : accidentDetails) {
      if (accident_detail.getId_AC().equals(accidentRepo.findOne(Long.parseLong(id)))) {    if(accident_detail.getId_user().getLat_PI()!=null&&accident_detail.getId_user().getLong_PI()!=null)
        UserFilterd.add(accident_detail.getId_user());
        logger.info(accident_detail.getId_user().toString());
      }
    }
    logger.info(accidentRepo.findOne(Long.parseLong(id)).toString());
    model.addAttribute("accident",accidentRepo.findOne(Long.parseLong(id)));
    model.addAttribute("accident_details",accidentService.GetAccidentDetailByAccidentID(Long.parseLong(id)));
      logger.info(UserFilterd.size()+"");

    model.addAttribute("users_joined",UserFilterd);
    model.addAttribute("googleMapsAPIKey", Util.GOOGLE_MAP_API_KEY);



    return "mainpage/accident_detail";
  }
}
