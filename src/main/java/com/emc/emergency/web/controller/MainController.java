package com.emc.emergency.web.controller;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.model.User_Type;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.data.repository.user_typeRepository;
import com.emc.emergency.service.AccidentService;
import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import com.emc.emergency.xmpp.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.emc.emergency.web.FlashMessage.Type_Mess.*;

import static com.emc.emergency.web.FlashMessage.Status.*;

/**
 * Created by hocan on 20-May-17.
 */
@Controller
public class MainController {
    // Home page - index of all GIFs
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    user_typeRepository userTypeRepository;
    @Autowired
    userRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    AccidentService accidentService;
    @Autowired
    accidentRepository accidentRepo;
    @RequestMapping("/")
    public String sign_in() {
        return "mainpage/login";
    }

    @RequestMapping("/login")
    public String sign_in2() {
        return "mainpage/login";
    }

    @RequestMapping("/home")
    public String home() {
        return "mainpage/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@Param("username")String username,@Param("password")String password) {
        FlashMessage flashMessage = new FlashMessage(LOGIN,"login",FAILURE);
        if(userService.Login(username,password)) flashMessage.setStatus(SUCCESS );
        return flashMessage.toString();

    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    @ResponseBody
    public String login2(@Param("username")String username,@Param("password")String password) {
        FlashMessage flashMessage = new FlashMessage(LOGIN,"login",FAILURE);
        if(userService.Login2(username,password)) flashMessage.setStatus(SUCCESS );
        return flashMessage.toString();

    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String home(@Param("username")String username,@Param("password")String password,Model model ) {
        List<User> users = userService.findAll();
        List<Accident> accidents = accidentService.GetAccident();
        model.addAttribute("userlist",users);
        model.addAttribute("accidentList",accidents);
        model.addAttribute("Message","Sai user name hoặc mật khẩu");
        if(userService.Login2(username,password)) return "mainpage/home";
        else {
            return "mainpage/login";
        }
    }

    @RequestMapping(value = "/accounts")
    public String UserIndex(Model model ) {
        List<User> users = userService.findAll();
        model.addAttribute("userlist",users);
        return "mainpage/user_index";
    }

    @RequestMapping("/register")
    public String register() {
        return "mainpage/register";
    }

    @RequestMapping("/controlpanel")
    public String controlpanel() {
        return "mainpage/register";
    }

    @RequestMapping("/forgot-password")
    public String forgot_password() {
        return "mainpage/forgot-password";
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public String refreshToken(@Param("token")String token,@Param("id_user")String id_user) {
        FlashMessage flashMessage = new FlashMessage(LOGIN,"login",FAILURE);
        User user = userRepository.findOne(Long.parseLong(id_user));
        if(user!=null) {
            // todo move to Services someday
            user.setToken(token);
            userRepository.save(user);
            flashMessage.setStatus(SUCCESS);
        }
        return flashMessage.toString();

    }

    @RequestMapping(value="account/changerole/{userID}", method= RequestMethod.POST)
    public String activate(
            @PathVariable("userID") String id, Model model
    ) {
        User user = userRepository.findOne(Long.parseLong(id));
        User_Type volunteer = userTypeRepository.findOne(2l);
        user.setUser_type(volunteer);
        userRepository.save(user);
        List<User> users = userService.findAll();
        model.addAttribute("userlist",users);
        return "mainpage/user_index";
    }

}
