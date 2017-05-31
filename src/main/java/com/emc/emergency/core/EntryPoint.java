package com.emc.emergency.core;


import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.CcsClient;
import com.emc.emergency.xmpp.MessageHelper;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry Point class for the XMPP Server in dev mode for debugging and testing purposes
 */
public class EntryPoint implements ApplicationRunner{

	public static final Logger logger = Logger.getLogger(EntryPoint.class.getName());

	@Override
	public void run(ApplicationArguments args) throws Exception {
	/*	final String fcmProjectSenderId = args[0];
		final String fcmServerKey = args[1];
		final String toRegId = args[2];

		CcsClient ccsClient = CcsClient.prepareClient(fcmProjectSenderId, fcmServerKey, true);

		try {
			ccsClient.connect();
		} catch (XMPPException | InterruptedException e) {
			e.printStackTrace();
		}

		// Send a sample downstream message to a device
		String messageId = Util.getUniqueMessageId();
		Map<String, String> dataPayload = new HashMap<String, String>();
		dataPayload.put(Util.PAYLOAD_ATTRIBUTE_MESSAGE, "This is the simple sample message");
		CcsOutMessage message = new CcsOutMessage(toRegId, messageId, dataPayload);
		String jsonRequest = MessageHelper.createJsonOutMessage(message);
		ccsClient.send(jsonRequest);

		try {
			CountDownLatch latch = new CountDownLatch(1);
			latch.await();
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, "An error occurred while latch was waiting.", e);
		}*/
	}
}
