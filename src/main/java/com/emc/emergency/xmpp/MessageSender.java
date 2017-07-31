package com.emc.emergency.xmpp;

import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.data.bean.StateResponse;
import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.util.Util;
import com.emc.emergency.web.controller.NoticeController;
import com.google.firebase.internal.Log;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hocan on 02-Jul-17.
 */
@Component
public class MessageSender {
    String TAG = "MessageSender";
    private static MessageSender instance;
    @PostConstruct
       void init() {
           instance = this;
       }

    @Autowired
    private static ApplicationContext appContext;

    @Autowired
    accidentRepository accidentRepository;

    @Autowired
    userRepository userRepository;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        appContext = context;
     }
    public static Object getBean(String beanName) {
       return appContext.getBean(beanName);
     }
    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());
    public MessageSender() {
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
    public void sendAccident( Accident accident,List<User> users, FCMService fcmService,String id_AC ,NoticeController noticeController) {
        int SoUserDaCall = 0;
        // 1km = 100.000 cm
        Double DefaultDistance = 1000000.0;
        String message = accident.getDescription_AC() + " ở " + accident.getAddress();

        List<User> userList = users;
        while (SoUserDaCall == 0 && DefaultDistance<=7000000.0) {

            for (Iterator<User> it = userList.iterator(); it.hasNext();) {
                    User user = it.next();
            if (user.getLong_PI() != null && user.getLat_PI() != null && user.getToken() != null) {
                if ((user.getId_user_type().getName_user_type().equals("volunteer") || (user.getId_user_type().getName_user_type().equals("admin")))) {

                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    logger.log(Level.INFO,user.toString());
                    Double distance  = Util.distFrom(
                            Double.valueOf(decimalFormat.format(user.getLat_PI())),
                            Double.valueOf(decimalFormat.format(user.getLong_PI())),
                            accident.getLat_AC(),
                            accident.getLong_AC());
                    logger.log(Level.INFO,"distance :" + distance);
                    // 3km = 300.000 cm
                    if (distance <=  DefaultDistance) {
                        SoUserDaCall++;
                        StateResponse stateresponse = new StateResponse();
                        try {
                            String messageId = Util.getUniqueMessageId();
                            Map<String, String> dataPayload = new HashMap<String, String>();
                            dataPayload.put(Util.BACKEND_ACTION_ACCIDENT, message);
                            dataPayload.put("title",accident.getDescription_AC()+"");
                            dataPayload.put("latitude",accident.getLat_AC()+"");
                            dataPayload.put("longtitude",accident.getLong_AC()+"");
                            dataPayload.put("address",accident.getAddress());
                            dataPayload.put("FirebaseKey",accident.getFirebaseKey());
                            dataPayload.put("id_AC",id_AC+"");
                            dataPayload.put("id_victim",accident.getId_user().getId_user()+"");

                            logger.log(Level.INFO,accident.getId_AC()+"");

                            CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                            Map<String, String> notiPayload = new HashMap<String, String>();
                            notiPayload.put(Util.BACKEND_ACTION_ACCIDENT, message);
                            notiPayload.put("title",accident.getDescription_AC()+"");
                            notiPayload.put("body",accident.getAddress()+"");
                            notiPayload.put("latitude",accident.getLat_AC()+"");
                            notiPayload.put("longtitude",accident.getLong_AC()+"");
                            notiPayload.put("address",accident.getAddress());
                            notiPayload.put("FirebaseKey",accident.getFirebaseKey());
                            notiPayload.put("id_AC",id_AC+"");
                            notiPayload.put("id_victim",accident.getId_user().getId_user()+"");

                            logger.log(Level.INFO,id_AC+"");

                            /** Gửi tin nhắn*/
                            out.setNotificationPayload(notiPayload);
                            /** Xóa  khỏi danh sách sẽ gửi**/
                            it.remove();

                            out.setPriority("High");
                            logger.log(Level.INFO,out.toString());

                            fcmService.sendMessage(out);
                            stateresponse.setCode(Util.OK_CODE);
                            stateresponse.setMessage(Util.OK_MESSAGE);
                            logger.log(Level.INFO,Util.OK_LABEL + message);
                            Log.d(TAG,"response :"+stateresponse.toString());

                            if(SoUserDaCall == 5)  break;

                        } catch (Exception e) {
                            stateresponse.setCode(Util.SERVER_ERROR_CODE);
                            stateresponse.setMessage(e.getMessage());
                            logger.log(Level.WARNING, Util.ERROR_LABEL + message);
                        }


                    }
                }
            }
        }
            DefaultDistance = DefaultDistance+1000000.0;

        }

       if(SoUserDaCall==0)    {
           noticeController.SendAlert("Cảnh báo tai nạn "+accident.getId_AC()+" không gọi được thông báo cho tnv nào cả. Vui lòng thông báo cho bệnh viện gần tai nạn nhất");

       } else noticeController.SendAlert("Đã gửi cho :"+ SoUserDaCall +" user");
    }

    /**
     * Gởi notification thông báo tai nạn đã xong cho user
     *
     * @param users user muốn gởi
     * @param fcmService đã khởi tạo sẵn
     * @param accident tai nạn cần gởi
     */
    public void sendAccidentDone( Accident accident,List<User> users, FCMService fcmService ,NoticeController noticeController) {
        int SoUserDaCall = 0 ;
        // 1km = 100.000 cm
        Double DefaultDistance = 1000000.0;

        String message = "Tai nạn ở " + accident.getAddress()+" đã xong";
        //  Log.d("OnAccidentCreated", message);
        List<User> userList = users;

        while (SoUserDaCall == 0 && DefaultDistance<=7000000.0 ) {
            DefaultDistance = DefaultDistance + 100000.0;
            for (Iterator<User> it = userList.iterator(); it.hasNext();) {
                User user = it.next();
            // chọn danh sách user có tọa độ và token
            if (user.getLong_PI() != null && user.getLat_PI() != null && user.getToken() != null) {
                if ((user.getId_user_type().getName_user_type().equals("volunteer") || (user.getId_user_type().getName_user_type().equals("admin")))) {
                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    logger.log(Level.INFO,user.toString());
                    Double distance  = Util.distFrom(
                        Double.valueOf(decimalFormat.format(user.getLat_PI())),
                        Double.valueOf(decimalFormat.format(user.getLong_PI())),
                        accident.getLat_AC(),
                        accident.getLong_AC());
                        logger.log(Level.INFO,"distance :" + distance);

                    if (distance <=  DefaultDistance) {
                        SoUserDaCall++;

                        StateResponse stateresponse = new StateResponse();
                        try {
                            String messageId = Util.getUniqueMessageId();
                            Map<String, String> dataPayload = new HashMap<String, String>();
                            dataPayload.put(Util.BACKEND_DONE_ACCIDENT, message);
                            dataPayload.put("latitude",accident.getLat_AC()+"");
                            dataPayload.put("longtitude",accident.getLong_AC()+"");
                            dataPayload.put("address",accident.getAddress());
                            dataPayload.put("FirebaseKey",accident.getFirebaseKey());
                            dataPayload.put("title",accident.getDescription_AC()+"");


                            CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                            Map<String, String> notiPayload = new HashMap<String, String>();
                            notiPayload.put("title","Tai nạn đã xong");
                            notiPayload.put("body",accident.getAddress()+"");
                            notiPayload.put("latitude",accident.getLat_AC()+"");
                            notiPayload.put("longtitude",accident.getLong_AC()+"");
                            notiPayload.put("address",accident.getAddress());
                            notiPayload.put("FirebaseKey",accident.getFirebaseKey());
                            notiPayload.put(Util.BACKEND_DONE_ACCIDENT, message);
                            out.setNotificationPayload(notiPayload);
                            out.setPriority("High");
                            logger.log(Level.INFO,out.toString());

                            /** Gửi tin nhắn*/
                          out.setNotificationPayload(notiPayload);

                            /** Xóa  khỏi danh sách sẽ gửi**/
                            it.remove();

                            stateresponse.setCode(Util.OK_CODE);
                            stateresponse.setMessage(Util.OK_MESSAGE);
                            logger.log(Level.INFO,Util.OK_LABEL + message);
                            Log.d(TAG,"response :"+stateresponse.toString());
                            if(SoUserDaCall == 5)  break;
                        } catch (Exception e) {
                            stateresponse.setCode(Util.SERVER_ERROR_CODE);
                            stateresponse.setMessage(e.getMessage());
                            logger.log(Level.WARNING, Util.ERROR_LABEL + message);
                        }

                    }
                }
            }

        }
            DefaultDistance += 1000000.0;


        }

        noticeController.SendAlert("Đã gửi cho :"+ SoUserDaCall +" user");

    }

    /**
     *  Gởi notification cho user
     * @param user user muốn gởi
     * @param fcmService đã khởi tạo sẵn
     * @param Message Tin nhắn
     * @param Title Tiêu đề
     */
    public void SendNotiToOneUser(User user, FCMService fcmService, String Message, String Title ,NoticeController noticeController) {
        StateResponse stateresponse = new StateResponse();

        try {
            if(user.getToken()!=null) {
                String messageId = Util.getUniqueMessageId();
                Map<String, String> dataPayload = new HashMap<String, String>();
                dataPayload.put("message", Message);
                dataPayload.put("title", Title);
                dataPayload.put(Util.BACKEND_ACTION_MESSAGE, Message);

                CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                Map<String, String> notiPayload = new HashMap<String, String>();
                notiPayload.put("title", Title);
                notiPayload.put("body", Message);
                notiPayload.put(Util.BACKEND_ACTION_MESSAGE, Message);

                out.setNotificationPayload(notiPayload);

                out.setPriority("High");
                logger.log(Level.INFO, out.toString());

                fcmService.sendMessage(out);

                stateresponse.setCode(Util.OK_CODE);
                stateresponse.setMessage(Util.OK_MESSAGE);
                logger.log(Level.INFO, Util.OK_LABEL + Message);
                Log.d(TAG, "response :" + stateresponse.toString());

                noticeController.sendNotice("Đã gửi cho thông báo cho: "+ user.getPersonal_Infomation().getName_PI());


            }

        } catch (Exception e) {
            stateresponse.setCode(Util.SERVER_ERROR_CODE);
            stateresponse.setMessage(e.getMessage());
            logger.log(Level.WARNING, Util.ERROR_LABEL + Message);
        }


    }
    /**
         *  Gởi notification cho user
         * @param users danh sách user muốn gởi
         * @param fcmService đã khởi tạo sẵn
         * @param Message Tin nhắn
         * @param Title Tiêu đề
         */
    public void SendNotiToAllUser(List<User> users, FCMService fcmService, String Message, String Title ,NoticeController noticeController) {
        StateResponse stateresponse = new StateResponse();
            for (User user : users)
               try {
                   if(user.getToken()!=null) {
                       String messageId = Util.getUniqueMessageId();
                       Map<String, String> dataPayload = new HashMap<String, String>();
                       dataPayload.put("message", Message);
                       dataPayload.put("title", Title);
                       dataPayload.put(Util.BACKEND_ACTION_MESSAGE, Message);

                       CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                       Map<String, String> notiPayload = new HashMap<String, String>();
                       notiPayload.put("title", Title);
                       notiPayload.put("body", Message);
                       notiPayload.put(Util.BACKEND_ACTION_MESSAGE, Message);

                       out.setNotificationPayload(notiPayload);

                       out.setPriority("High");
                       logger.log(Level.INFO, out.toString());

                       fcmService.sendMessage(out);

                       stateresponse.setCode(Util.OK_CODE);
                       stateresponse.setMessage(Util.OK_MESSAGE);
                       logger.log(Level.INFO, Util.OK_LABEL + Message);
                       Log.d(TAG, "response :" + stateresponse.toString());



                   }

               } catch (Exception e) {
                   stateresponse.setCode(Util.SERVER_ERROR_CODE);
                   stateresponse.setMessage(e.getMessage());
                   logger.log(Level.WARNING, Util.ERROR_LABEL + Message);
               }
              noticeController.sendNotice("Đã gửi cho thông báo cho tất cả mọi người");


       }



    /**
     *  Gởi notification cho user
     * @param user user muốn gởi
     * @param fcmService đã khởi tạo sẵn
     * @param Message Tin nhắn
     * @param Title Tiêu đề
     */
    public void SendLockToOneUser(User user, FCMService fcmService, String Message, String Title ,NoticeController noticeController) {
        StateResponse stateresponse = new StateResponse();

        try {
            if(user.getToken()!=null) {
                String messageId = Util.getUniqueMessageId();
                Map<String, String> dataPayload = new HashMap<String, String>();
                dataPayload.put("message", Message);
                dataPayload.put("title", Title);
                dataPayload.put(Util.BACKEND_ACTION_LOCK_USER, Message);

                CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);

                Map<String, String> notiPayload = new HashMap<String, String>();
                notiPayload.put("title", Title);
                notiPayload.put("body", Message);
                notiPayload.put(Util.BACKEND_ACTION_LOCK_USER, Message);

                out.setNotificationPayload(notiPayload);

                out.setPriority("High");
                logger.log(Level.INFO, out.toString());

                fcmService.sendMessage(out);

                stateresponse.setCode(Util.OK_CODE);
                stateresponse.setMessage(Util.OK_MESSAGE);
                logger.log(Level.INFO, Util.OK_LABEL + Message);
                Log.d(TAG, "response :" + stateresponse.toString());



            }

        } catch (Exception e) {
            stateresponse.setCode(Util.SERVER_ERROR_CODE);
            stateresponse.setMessage(e.getMessage());
            logger.log(Level.WARNING, Util.ERROR_LABEL + Message);
        }


    }
}
