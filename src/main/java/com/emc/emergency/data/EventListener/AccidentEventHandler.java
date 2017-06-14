package com.emc.emergency.data.EventListener;

import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.data.bean.StateResponse;
import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.util.Util;
import com.emc.emergency.web.controller.FCMController;
import com.emc.emergency.xmpp.CcsClient;
import com.google.firebase.internal.Log;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.xml.bind.JAXBIntrospector.getValue;

/**
 * Xử lý sau khi create accident
 * Cài đặt bean trong CustomRestMvcConfiguration.java
 */
@RepositoryEventHandler(Accident.class)
public class AccidentEventHandler {
    String TAG = "AccidentEventHandler";

    @Autowired
    accidentRepository accidentRepository;

    @Autowired
    userRepository  userRepository;

    @Autowired
    private FCMService fcmService;
    private static final Logger logger = LoggerFactory.getLogger(AccidentEventHandler.class);

    public AccidentEventHandler() {
        CcsClient ccsClient = CcsClient.prepareClient("728085231482", "AAAAqYVC93o:APA91bH_L5bG6M_OINOatTEfUZ4YpW5Lec7CeG8C33oXkdxtS2Pga61LN2S4fwh9OF7VW3T-1CJtca7wz-bunK4MAvul4A_cf4vdPCFcNWvx-NzOtSvHs6Qls9VYq4vn6G4FWDlJIyKT", true);
        try {
            ccsClient.connect();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SmackException e) {
            e.printStackTrace();
        }
    }
    @HandleAfterCreate
    public void handleAccidentCreate(Accident accident){
        String message = accident.getDescription_AC();

        Iterable<User> userList = userRepository.findAll();
        for (User user : userList) {
            if(user.getLong_PI()!=null&&user.getLat_PI()!=null&&user.getToken()!=null){
                DecimalFormat decimalFormat = new DecimalFormat("#");
                Log.d(TAG,user.toString());
                Float distance = Util.distFrom(
                        Float.valueOf(decimalFormat.format(user.getLat_PI())),
                        Float.valueOf(decimalFormat.format(user.getLong_PI())),
                        accident.getLat_AC(),
                        accident.getLong_AC());
                Log.d(TAG,"distance :"+distance);
                if(distance < 3000.0) {
                    StateResponse response = new StateResponse();
                    try {
                        String messageId = Util.getUniqueMessageId();
                        Map<String, String> dataPayload = new HashMap<String, String>();
                        dataPayload.put(Util.BACKEND_ACTION_ACCIDENT, message);
                        CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                        fcmService.sendMessage(out);

                        response.setCode(Util.OK_CODE);
                        response.setMessage(Util.OK_MESSAGE);
                        logger.debug(Util.OK_LABEL + message);
                        Log.d(TAG,"response :"+response.toString());
                    } catch (Exception e) {
                        response.setCode(Util.SERVER_ERROR_CODE);
                        response.setMessage(e.getMessage());
                        logger.error(Util.ERROR_LABEL + e.getMessage());
                    }

                }
            }
        }

    }


}
