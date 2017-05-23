package com.emc.emergency.web.controller;

import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.emc.emergency.web.FlashMessage.Status.*;
import static com.emc.emergency.web.FlashMessage.Type_Mess.*;

/**
 * Created by hocan on 20-May-17.
 */
@RestController
public class Rest_Controller {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String Login (@RequestParam("username")String username, @RequestParam("password")String password) {
        FlashMessage flashMessage = new FlashMessage(LOGIN,"login",FAILURE);
        if(userService.Login(username,password)) flashMessage.setStatus(SUCCESS);
        return flashMessage.toString();
    }
    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String Register (@RequestParam("username")String username, @RequestParam("password")String password) {
        FlashMessage flashMessage = new FlashMessage(REGISTER,"register",FAILURE);
        if(userService.Register(username,password)) flashMessage.setStatus(SUCCESS);
        return flashMessage.toString();
    }



}
