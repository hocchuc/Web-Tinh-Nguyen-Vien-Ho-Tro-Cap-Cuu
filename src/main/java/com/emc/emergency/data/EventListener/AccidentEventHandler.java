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
import org.json.JSONArray;
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
        if(accident.getAddress()==null) {

            Response response = null;
            String url =  "http://api.openfpt.vn/ftsrouting/nearest?loc="+accident.getLat_AC()+"%2C"+accident.getLong_AC();
            String urlGGAPI = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+accident.getLat_AC()+"%2C"+accident.getLong_AC()+"&key="+Util.GOOGLE_MAP_API_KEY;
            logger.log(Level.INFO,urlGGAPI);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlGGAPI)
                    .get()
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                logger.log(Level.INFO,jsonObject.toString());

                JSONArray jsonArray = jsonObject.getJSONArray("results");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.has("formatted_address"))
                    accident.setAddress(jsonObject1.getString("formatted_address"));
                // nếu không lấy được cái đầu tiên
                if(accident.getAddress()==null) {
                    jsonObject1 = jsonArray.getJSONObject(1);
                    if (jsonObject1.has("formatted_address"))
                        accident.setAddress(jsonObject1.getString("formatted_address"));
                }
                logger.log(Level.INFO,accident.toString());

                accidentRepository.save(accident);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        String message = accident.getDescription_AC() +" ở "+ accident.getAddress();
//        Log.d("OnAccidentCreated",message);
//        Iterable<User> userList = userRepository.findAll();
//        for (User user : userList) {
//            if(user.getLong_PI()!=null&&user.getLat_PI()!=null&&user.getToken()!=null){
//                if((user.getId_user_type().getName_user_type().equals("volunteer")||(user.getId_user_type().getName_user_type().equals("admin")))){
//
//                DecimalFormat decimalFormat = new DecimalFormat("#");
//                Log.d(TAG,user.toString());
//                Double distance = Util.distFrom(
//                        Double.valueOf(decimalFormat.format(user.getLat_PI())),
//                        Double.valueOf(decimalFormat.format(user.getLong_PI())),
//                        accident.getLat_AC(),
//                        accident.getLong_AC());
//                Log.d(TAG,"distance :"+distance);
//                // 3km = 300.000 cm
//                if(distance < 30000000.0) {
//                    StateResponse stateresponse = new StateResponse();
//                    try {
//                        String messageId = Util.getUniqueMessageId();
//                        Map<String, String> dataPayload = new HashMap<String, String>();
//                        dataPayload.put(Util.BACKEND_ACTION_ACCIDENT, message);
//                        dataPayload.put("latitude",accident.getLat_AC()+"");
//                        dataPayload.put("longtitude",accident.getLong_AC()+"");
//                        dataPayload.put("address",accident.getAddress());
//                        dataPayload.put("FirebaseKey",accident.getFirebaseKey());
//
//                        CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);
//
//                        Map<String, String> notiPayload = new HashMap<String, String>();
//                        notiPayload.put(Util.BACKEND_ACTION_ACCIDENT, message);
//                        notiPayload.put("latitude",accident.getLat_AC()+"");
//                        notiPayload.put("longtitude",accident.getLong_AC()+"");
//                        notiPayload.put("address",accident.getAddress());
//                        notiPayload.put("FirebaseKey",accident.getFirebaseKey());
//                        out.setNotificationPayload(notiPayload);
//
//                        out.setPriority("High");
//                        logger.log(Level.INFO,out.toString());
//
//                        fcmService.sendMessage(out);
//
//                        stateresponse.setCode(Util.OK_CODE);
//                        stateresponse.setMessage(Util.OK_MESSAGE);
//                        logger.log(Level.INFO,Util.OK_LABEL + message);
//                        Log.d(TAG,"response :"+stateresponse.toString());
//                    } catch (Exception e) {
//                        stateresponse.setCode(Util.SERVER_ERROR_CODE);
//                        stateresponse.setMessage(e.getMessage());
//                        logger.log(Level.WARNING,Util.ERROR_LABEL + message);
//                    }
//
//                }
//                }
//            }
//        }


    }


}
