package com.emc.emergency.service.impl;


import com.emc.emergency.data.bean.CcsInMessage;
import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.service.PayloadProcessor;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.CcsClient;
import com.emc.emergency.xmpp.MessageHelper;
import org.springframework.stereotype.Service;

/**
 * Handles an upstream message request
 */
@Service

public class MessageProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsInMessage inMessage) {
		CcsClient client = CcsClient.getInstance();
		String messageId = Util.getUniqueMessageId();
		String to = inMessage.getDataPayload().get(Util.PAYLOAD_ATTRIBUTE_RECIPIENT);

		// TODO: handle the data payload sent to the client device. Here, I just
		// resend the incoming message.
		CcsOutMessage outMessage = new CcsOutMessage(to, messageId, inMessage.getDataPayload());
		String jsonRequest = MessageHelper.createJsonOutMessage(outMessage);
		client.send(jsonRequest);
	}

}