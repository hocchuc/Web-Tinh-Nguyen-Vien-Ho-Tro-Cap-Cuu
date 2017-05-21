package com.emc.emergency.web.controller;

import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.emc.emergency.web.FlashMessage.Status.FAILURE;
import static com.emc.emergency.web.FlashMessage.Status.SUCCESS;

/**
 * Created by hocan on 20-May-17.
 */
@RestController
@RequestMapping(value="/api", produces={"application/xml", "application/json"})
public class Rest_Controller {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String Login (@Param("username")String username, @Param("password")String password) {
        FlashMessage flashMessage = new FlashMessage("login",FAILURE);
        if(userService.Login(username,password)) flashMessage.setStatus(FAILURE);
        return flashMessage.toString();
    }

}
