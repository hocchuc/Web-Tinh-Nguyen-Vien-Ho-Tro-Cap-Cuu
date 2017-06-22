package com.emc.emergency.data.EventListener;

import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.data.bean.StateResponse;
import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.CcsClient;
import com.google.firebase.internal.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static final Logger logger = Logger.getLogger(CcsClient.class.getName());

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
        if(accident.getAdress()==null) {

            Response response = null;
            String url =  "http://api.openfpt.vn/ftsrouting/nearest?loc="+accident.getLat_AC()+"%2C"+accident.getLong_AC();
            logger.log(Level.INFO,url);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("api_key", Util.OPEN_FPT_API_KEY)
                    .addHeader("cache-control", "no-cache")
                    .build();
            // logger.log(Level.INFO,request.body().toString());

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                if (jsonObject.has("name"))
                    accident.setAdress(jsonObject.getString("name"));
                // logger.log(Level.INFO, Util.OK_LABEL+" "+request.body().toString());


                if (jsonObject.has("status"))
                    if(jsonObject.getInt("status")!=0){
                        accident.setAdress("Không nằm trong dữ liệu Việt Nam");
                    }

                accidentRepository.save(accident);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String message = accident.getDescription_AC() +" ở "+ accident.getAdress();
        Log.d("OnAccidentCreated",message);
        Iterable<User> userList = userRepository.findAll();
        for (User user : userList) {
            if(user.getLong_PI()!=null&&user.getLat_PI()!=null&&user.getToken()!=null){
                DecimalFormat decimalFormat = new DecimalFormat("#");
                Log.d(TAG,user.toString());
                Double distance = Util.distFrom(
                        Double.valueOf(decimalFormat.format(user.getLat_PI())),
                        Double.valueOf(decimalFormat.format(user.getLong_PI())),
                        accident.getLat_AC(),
                        accident.getLong_AC());
                Log.d(TAG,"distance :"+distance);
                // 3km = 300.000 cm
                if(distance < 300000000.0) {
                    StateResponse stateresponse = new StateResponse();
                    try {
                        String messageId = Util.getUniqueMessageId();
                        Map<String, String> dataPayload = new HashMap<String, String>();
                        dataPayload.put(Util.BACKEND_ACTION_ACCIDENT, message);
                        dataPayload.put("latitude",accident.getLat_AC()+"");
                        dataPayload.put("longtitude",accident.getLong_AC()+"");
                        dataPayload.put("address",accident.getAdress());
                        dataPayload.put("FirebaseKey",accident.getFirebaseKey());
                        CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                        fcmService.sendMessage(out);

                        stateresponse.setCode(Util.OK_CODE);
                        stateresponse.setMessage(Util.OK_MESSAGE);
                        logger.log(Level.INFO,Util.OK_LABEL + message);
                        Log.d(TAG,"response :"+stateresponse.toString());
                    } catch (Exception e) {
                        stateresponse.setCode(Util.SERVER_ERROR_CODE);
                        stateresponse.setMessage(e.getMessage());
                        logger.log(Level.WARNING,Util.ERROR_LABEL + message);
                    }

                }
            }
        }


    }


}
