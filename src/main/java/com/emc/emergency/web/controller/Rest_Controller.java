package com.emc.emergency.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocan on 20-May-17.
 */
@RestController
public class Rest_Controller {
    @RequestMapping("/api")
    @ResponseBody
    public String hello (Model model) {

        return "hello";
    }
}
