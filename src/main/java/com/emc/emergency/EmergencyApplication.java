package com.emc.emergency;

import com.emc.emergency.data.bean.CcsOutMessage;
import com.emc.emergency.util.Util;
import com.emc.emergency.xmpp.CcsClient;
import com.emc.emergency.xmpp.MessageHelper;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class EmergencyApplication {
	public static final Logger logger = Logger.getLogger(EmergencyApplication.class.getName());
		@PostConstruct
			void started() {
				TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			}
	public static void main(String[] args)throws SmackException, IOException  {
		SpringApplication.run(EmergencyApplication.class, args);

	}
}
