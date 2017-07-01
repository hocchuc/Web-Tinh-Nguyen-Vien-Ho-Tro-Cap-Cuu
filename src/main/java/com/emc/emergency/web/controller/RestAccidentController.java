package com.emc.emergency.web.controller;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.User;
import com.emc.emergency.service.AccidentService;
import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.emc.emergency.web.FlashMessage.Status.FAILURE;
import static com.emc.emergency.web.FlashMessage.Status.SUCCESS;
import static com.emc.emergency.web.FlashMessage.Type_Mess.CREATED;
import static com.emc.emergency.web.FlashMessage.Type_Mess.REGISTER;

/**
 * Created by hocan on 24-May-17.
 */
@RestController
public class RestAccidentController {
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }
    @Autowired
    AccidentService accidentService;

    @RequestMapping(value = "/api/accident/create", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String CreateAccident ( @RequestParam("id_user") Long id_user, @RequestParam("des_AC")String description_AC,@RequestParam("date_AC") Date date_AC,@RequestParam("long_AC") Float long_AC,@RequestParam("lat_AC") Float lat_AC,@RequestParam("status_AC") String status_AC) {
        FlashMessage flashMessage = new FlashMessage(CREATED,"created",FAILURE);

        try {
            accidentService.CreateAccident(null, id_user, description_AC,  date_AC, long_AC, lat_AC, status_AC);
        } catch (Exception e) {
            e.printStackTrace();
            flashMessage.setStatus(SUCCESS);
        }
        return flashMessage.toString();
    }

    @RequestMapping(value = "/api/accident/get", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Accident> GetAccident () {
        return accidentService.GetAccident();
    }
}
