package com.emc.emergency.web.controller;

import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.User;
import com.emc.emergency.service.AccidentService;
import com.emc.emergency.service.UserService;
import com.emc.emergency.web.FlashMessage;
import com.google.api.client.json.Json;
import java.text.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.emc.emergency.web.FlashMessage.Status.FAILURE;
import static com.emc.emergency.web.FlashMessage.Status.SUCCESS;
import static com.emc.emergency.web.FlashMessage.Type_Mess.CREATED;
import static com.emc.emergency.web.FlashMessage.Type_Mess.LOGIN;
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


    @RequestMapping(value = "/api/accident/join", method = RequestMethod.POST,
        consumes = MediaType.ALL_VALUE,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String JoinAccident(@RequestBody String jsonBody) throws ParseException,JsonParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");
        String id_user = "0L", id_AC = "0L";
        Date date = null;
        JSONObject jsonObject = new JSONObject(jsonBody);
        if(jsonObject.has("id_user")) id_user = jsonObject.getString("id_user");
        if(jsonObject.has("id_AC")) id_AC = jsonObject.getString("id_AC");
        if(jsonObject.has("date")) date = formatter.parse(jsonObject.getString("date"));
        if(accidentService.CreateAccidentDetail(Long.parseLong(id_user),Long.parseLong(id_AC),1l,date)) return "Success";
        return "Failure" ;
    }

    @RequestMapping(value = "/api/accident/action", method = RequestMethod.POST,
        consumes = MediaType.ALL_VALUE,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String CreateAction(@RequestBody String jsonBody) throws ParseException,JsonParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");
        String id_user = "0", id_AC = "0", id_action_type = "0";
        Date date = null;

        JSONObject jsonObject = new JSONObject(jsonBody);
        if(jsonObject.has("id_user")) id_user = jsonObject.getString("id_user");
        if(jsonObject.has("id_AC")) id_AC = jsonObject.getString("id_AC");
        if(jsonObject.has("id_action_type")) id_action_type = jsonObject.getString("id_action_type");
        if(jsonObject.has("date")) date = formatter.parse(jsonObject.getString("date"));
        if(accidentService.CreateAccidentDetail(Long.parseLong(id_user),Long.parseLong(id_AC),
            Long.valueOf(id_action_type),date)) return "Success";
        return "Failure" ;
    }


}
