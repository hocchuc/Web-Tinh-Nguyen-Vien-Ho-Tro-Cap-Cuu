package com.emc.emergency.web.controller;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.service.AccidentService;
import com.emc.emergency.service.UserService;
import com.emc.emergency.xmpp.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    // Home page - index of all GIFs
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;
    @Autowired
    AccidentService accidentService;
    @Autowired
    accidentRepository accidentRepo;


    @RequestMapping(value = "/accidents")
    public String AccidentIndex(Model model ) {
        List<Accident> accidents = accidentService.GetAccident();
        model.addAttribute("accidents",accidents);
        return "mainpage/accident_index";
    }



    @RequestMapping(value="accident/remove/{accidentID}", method= RequestMethod.POST)
    public String remove(
            @PathVariable("accidentID") String id, Model model
    ) {
        accidentService.removeOne((Long.parseLong(id)));
        List<Accident> accidents = accidentService.GetAccident();
        model.addAttribute("accidents",accidents);

        return "mainpage/accident_index";
    }

    @RequestMapping(value="accident/activate/{accidentID}", method= RequestMethod.POST)
    public String activate(
            @PathVariable("accidentID") String id, Model model
    ) {
        accidentService.activate(Long.parseLong(id));
        List<Accident> accidents = accidentService.GetAccident();
        MessageSender messageSender = new MessageSender();
        messageSender.sendAccident(accidentRepo.findOne(Long.parseLong(id)));
        model.addAttribute("accidents",accidents);

        return "mainpage/accident_index";
    }
}
