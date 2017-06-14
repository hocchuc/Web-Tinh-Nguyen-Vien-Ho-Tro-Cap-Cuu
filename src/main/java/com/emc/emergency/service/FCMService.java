package com.emc.emergency.service;


/*
 * FCMService Interface
 */

import com.emc.emergency.data.bean.CcsOutMessage;

public interface FCMService {

	public void sendMessage(CcsOutMessage message);

}
