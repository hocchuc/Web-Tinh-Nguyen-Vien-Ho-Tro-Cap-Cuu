package com.emc.emergency.web.controller;

import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.emc.emergency.web.FlashMessage.Status.*;
import static com.emc.emergency.web.FlashMessage.Type_Mess.*;

/**
 * Created by hocan on 20-May-17.
 */
@RestController
public class RestUserController {
    @Autowired
    UserService userService;
    @Autowired
    userRepository userRepository;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,produces = "application/json")
    @ResponseBody
    public FlashMessage Login (@RequestBody User user) {
        FlashMessage flashMessage = new FlashMessage(LOGIN,"login",FAILURE);
        if(userService.Login(user.getUsername(),user.getPassword())) {
            User user1 = userRepository.findByUsername(user.getUsername());
            if(user1 !=null) {
                user1.setLat_PI(user.getLat_PI());
                user1.setLong_PI(user.getLong_PI());
                userRepository.save(user1);
            }

            flashMessage.setStatus(SUCCESS);
            flashMessage.setMessage((userRepository.findByUsername(user.getUsername()).getId_user())+"");
        }
        return flashMessage;
    }
    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public FlashMessage Register (@RequestBody User user) {
        FlashMessage flashMessage = new FlashMessage(REGISTER,"register",FAILURE);
        if(userService.Register(user.getUsername(),user.getPassword())) {
            flashMessage.setStatus(SUCCESS);
            flashMessage.setMessage(userRepository.findByUsername(user.getUsername()).getId_user()+"");
        }
        return flashMessage;
    }
    @RequestMapping(value = "/api/refreshToken", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FlashMessage refreshToken (@RequestParam(value="token") String token,
                                      @RequestParam(value="id_user") Long id_user) {
        FlashMessage flashMessage = new FlashMessage(TOKEN,"refreshToken",FAILURE);
        User user = userRepository.findOne(Long.valueOf(id_user));
        if(user!=null){
            user.setToken(token);
            userRepository.save(user);
            flashMessage.setStatus(SUCCESS);
        }
        return flashMessage;
    }

    @RequestMapping(value = "/api/postLocation", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FlashMessage postLocation (@RequestParam(value="lat") String lat,
                                      @RequestParam(value="lon") String lon,
                                      @RequestParam(value="id_user") Long id_user) {
        FlashMessage flashMessage = new FlashMessage(TOKEN,"refreshToken",FAILURE);
        User user = userRepository.findOne(Long.valueOf(id_user));
        if(user!=null){
            user.setLat_PI(Double.valueOf(lat));
            user.setLong_PI(Double.valueOf(lon));
            userRepository.save(user);
            flashMessage.setStatus(SUCCESS);
        }
        return flashMessage;
    }







}
