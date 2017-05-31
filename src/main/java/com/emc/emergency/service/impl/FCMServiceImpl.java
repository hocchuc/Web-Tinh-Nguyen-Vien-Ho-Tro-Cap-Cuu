package com.emc.emergency.service.impl;


import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.service.FCMService;
import com.emc.emergency.xmpp.CcsClient;
import com.emc.emergency.xmpp.MessageHelper;
import org.springframework.stereotype.Service;

/*
 * FCMServiceImpl
 */

@Service
public class FCMServiceImpl implements FCMService {

	/*
	 * Sends a message to a device through FCM
	 */
	@Override
	public void sendMessage(final CcsOutMessage message) {
		String jsonRequest = MessageHelper.createJsonOutMessage(message);
		CcsClient.getInstance().send(jsonRequest);
	}

}
