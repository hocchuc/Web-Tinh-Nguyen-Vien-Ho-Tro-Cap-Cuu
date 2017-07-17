package com.emc.emergency.data.EventListener;

import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.data.bean.StateResponse;
import com.emc.emergency.data.model.Accident;
import com.emc.emergency.data.model.Accident_Detail;
import com.emc.emergency.data.model.User;
import com.emc.emergency.data.repository.accidentRepository;
import com.emc.emergency.data.repository.accident_detailRepository;
import com.emc.emergency.data.repository.userRepository;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.CcsClient;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

/**
 * Created by hocan on 17-Jul-17.
 */

@RepositoryEventHandler(Accident_Detail.class)
public class AccidentDetailEventHandler {
  String TAG = "AccidentDetailEventHandler";
  Accident accident = new Accident();

  @Autowired
  accidentRepository accidentRepository;

  @Autowired
  userRepository userRepository;

  @Autowired
  accident_detailRepository accident_detailRepository;

  @Autowired
  private FCMService fcmService;
  private static final Logger logger = Logger.getLogger(AccidentDetailEventHandler.class.getName());

  public AccidentDetailEventHandler() {
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
  public void handleAccidentDetailCreate(Accident_Detail accident_detail) {
    Iterable<User> userList1;
    logger.log(Level.INFO, "handleAccidentDetailCreate");

    userList1 = userRepository.findAll();
    String ActionName = accident_detail.getAction_type().getName_action();
    logger.log(Level.INFO, ActionName);
    switch (ActionName) {
      case ("Join"): {
        Iterable<Accident_Detail> accidentDetails;
        accidentDetails = accident_detailRepository.findAll();
        accident = accidentRepository.findOne(accident_detail.getId_AC().getId_AC());
        Boolean is_duplicate = false;
        for (Accident_Detail accidentdel : accidentDetails){
          // compare 3 long - -
          if(accident_detail.getId_user().getId_user().equals(accidentdel.getId_user().getId_user())&&
              accident_detail.getAction_type().getId_action().equals(accidentdel.getAction_type().getId_action())&&accident_detail.getId_AC().getId_AC().equals(accidentdel.getId_AC().getId_AC()))
          {
            is_duplicate=true;
            break;
          }
        }

        if(is_duplicate) {
          accident_detailRepository.delete(accident_detail);
        }
        else{
          accident.setJoined(accident.getJoined()+1);
          accidentRepository.save(accident);
          String message = "Tai nạn ở " + accident.getAddress();
          String Tag = Util.BACKEND_ACTION_ACCIDENT;
          SendNotiToAllUser(accident_detail, message, Tag,userList1);
        }

        break;
      }
      case ("SetDone"): {
        accident = accidentRepository.findOne(accident_detail.getId_AC().getId_AC());
        accident.setStatus_AC("Done");
        accidentRepository.save(accident);
        String message = "Tai nạn ở " + accident.getAddress();
        String Tag = Util.BACKEND_ACTION_ACCIDENT;
        SendNotiToAllUser(accident_detail, message, Tag,userList1);
        break;
      }
      case ("ReportFake"): {
        accident = accidentRepository.findOne(accident_detail.getId_AC().getId_AC());
        accident.setIs_reported_fake(true);
        accidentRepository.save(accident);

        String message = "Tai nạn giả " + accident.getAddress();
        String Tag = Util.BACKEND_FAKE_ACCIDENT;
        SendNotiToAllUser(accident_detail, message, Tag,userList1);
        break;
      }
    }
  }

  public void SendNotiToAllUser(Accident_Detail accident_detail, String message, String Tag, Iterable<User> userList) {
    {

      logger.log(Level.INFO, accident.toString());

      for (User user : userList) {
        if (user.getLong_PI() != null && user.getLat_PI() != null && user.getToken() != null) {
          if ((user.getId_user_type().getName_user_type().equals("volunteer")
              || (user.getId_user_type().getName_user_type().equals("admin")))) {

            DecimalFormat decimalFormat = new DecimalFormat("#");
            Double distance = Util.distFrom(
                Double.valueOf(decimalFormat.format(user.getLat_PI())),
                Double.valueOf(decimalFormat.format(user.getLong_PI())),
                accident.getLat_AC(),
                accident.getLong_AC());
            // 3km = 300.000 cm
            if (distance < 30000000.0) {
              StateResponse stateresponse = new StateResponse();
              try {
                String messageId = Util.getUniqueMessageId();
                Map<String, String> dataPayload = new HashMap<String, String>();
                dataPayload.put(Util.BACKEND_FAKE_ACCIDENT, message);
                CcsOutMessage out = new CcsOutMessage(user.getToken(), messageId, dataPayload);
                Map<String, String> notiPayload = new HashMap<String, String>();
                notiPayload.put(Util.BACKEND_FAKE_ACCIDENT, message);
                out.setNotificationPayload(notiPayload);
                logger.log(Level.INFO, out.toString());
                fcmService.sendMessage(out);
                stateresponse.setCode(Util.OK_CODE);
                stateresponse.setMessage(Util.OK_MESSAGE);
                logger.log(Level.INFO, Util.OK_LABEL + message);
              } catch (Exception e) {
                stateresponse.setCode(Util.SERVER_ERROR_CODE);
                stateresponse.setMessage(e.getMessage());
                logger.log(Level.WARNING, Util.ERROR_LABEL + message);
              }
            }
          }
        }
      }

    }
  }
}

