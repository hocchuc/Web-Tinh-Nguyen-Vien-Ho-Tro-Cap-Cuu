package com.emc.emergency.service.impl;


import com.emc.emergency.data.bean.CcsInMessage;
import com.emc.emergency.service.PayloadProcessor;
import org.springframework.stereotype.Service;

/**
 * Handles a user registration request
 */
@Service
public class RegisterProcessor implements PayloadProcessor {

	@Override
	public void handleMessage(CcsInMessage msg) {
		// TODO: handle the user registration. Keep in mind that a user name can
		// have more reg IDs associated. The messages IDs should be uniques. 
	}

}