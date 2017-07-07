package com.emc.emergency.web.controller;


import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.data.bean.StateResponse;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.CcsClient;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * FCMController: main fcm rest api
 */

@Controller
@RequestMapping("/fcm")
public class FCMController {

	private static final Logger logger = LoggerFactory.getLogger(FCMController.class);

	@Autowired
	private FCMService fcmService;

	public FCMController() {
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

	/* Send a message to a device. HTTP Method: GET */
	/*
	 * http://localhost:8080/notifierServer/fcm/send?token=&message=
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	StateResponse sendMessage(@Param("token")String token,@Param("message")String message) {

		StateResponse response = new StateResponse();

		try {
			String messageId = Util.getUniqueMessageId();
			Map<String, String> dataPayload = new HashMap<String, String>();
			dataPayload.put(Util.PAYLOAD_ATTRIBUTE_MESSAGE, message);
			CcsOutMessage out = new CcsOutMessage(token, messageId, dataPayload);

			fcmService.sendMessage(out);

			response.setCode(Util.OK_CODE);
			response.setMessage(Util.OK_MESSAGE);
			logger.debug(Util.OK_LABEL + message);
		} catch (Exception e) {
			response.setCode(Util.SERVER_ERROR_CODE);
			response.setMessage(e.getMessage());
			logger.error(Util.ERROR_LABEL + e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	String registerToken(
			@RequestParam(value = "token", defaultValue = "", required = true) String token,
			@RequestParam(value = "user_id", defaultValue = "", required = true) String user_id) {

		return null;
	}

}
