package com.emc.emergency.web.controller;

import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.emc.emergency.web.FlashMessage.Status.FAILURE;
import static com.emc.emergency.web.FlashMessage.Status.SUCCESS;

/**
 * Created by hocan on 20-May-17.
 */
@RestController
public class Rest_Controller {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public String Login (@RequestParam("username")String username, @RequestParam("password")String password) {
        FlashMessage flashMessage = new FlashMessage("login",SUCCESS);
        if(userService.Login(username,password)) flashMessage.setStatus(FAILURE);
        return flashMessage.toString();
    }
    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    @ResponseBody
    public String Register (@RequestParam("username")String username, @RequestParam("password")String password) {
        FlashMessage flashMessage = new FlashMessage("register",FAILURE);
        if(userService.Register(username,password)) flashMessage.setStatus(SUCCESS);
        return flashMessage.toString();
    }

}
