package com.emc.emergency.web.controller;

import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static com.emc.emergency.web.FlashMessage.Type_Mess.*;

import static com.emc.emergency.web.FlashMessage.Status.*;

/**
 * Created by hocan on 20-May-17.
 */
@Controller
public class MainController {
    // Home page - index of all GIFs
    UserService userService;
    @RequestMapping("/")
    public String sign_in() {
        return "mainpage/login";
    }
    @RequestMapping("/login")
    public String sign_in2() {
        return "mainpage/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Param("username")String username,@Param("password")String password) {
        FlashMessage flashMessage = new FlashMessage(LOGIN,"login",FAILURE);
        if(userService.Login(username,password)) flashMessage.setStatus(FAILURE);
        return flashMessage.toString();

    }
    @RequestMapping("/register")
    public String register() {
        return "mainpage/register";
    }
    @RequestMapping("/forgot-password")
    public String forgot_password() {
        return "mainpage/forgot-password";
    }
}
