package com.emc.emergency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hocan on 20-May-17.
 */
@Controller
public class MainController {
    // Home page - index of all GIFs
    @RequestMapping("/")
    public String sign_in() {
        return "mainpage/login";
    }
    @RequestMapping("/login")
    public String sign_in2() {
        return "mainpage/login";
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
