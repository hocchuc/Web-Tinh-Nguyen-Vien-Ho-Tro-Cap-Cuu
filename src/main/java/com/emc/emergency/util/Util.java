package com.emc.emergency.util;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.UUID;

/*
 * Util Class
 */
public class Util {

	// For general purpose
	public static final int OK_CODE = 200;
	public static final int NOT_FOUND_ERROR_CODE = 404;
	public static final int SERVER_ERROR_CODE = 500;
	public static final String OK_MESSAGE = "OK";
	public static final String ERROR_LABEL = "ERROR ---> ";
	public static final String OK_LABEL = "OK --->";

	// For the GCM connection
	public static final String FCM_SERVER = "fcm-xmpp.googleapis.com";
	public static final int FCM_PORT = 5236;
	public static final String FCM_ELEMENT_NAME = "gcm";
	public static final String FCM_NAMESPACE = "google:mobile:data";
	public static final String FCM_SERVER_CONNECTION = "gcm.googleapis.com";

	// For the processor factory
	public static final String PACKAGE = "com.emc.emergency";
	public static final String BACKEND_ACTION_REGISTER = PACKAGE + ".REGISTER";
	public static final String BACKEND_ACTION_ECHO = PACKAGE + ".ECHO";
	public static final String BACKEND_ACTION_MESSAGE = PACKAGE + ".MESSAGE";

	// For the app common payload message attributes (android - xmpp server)
	public static final String PAYLOAD_ATTRIBUTE_MESSAGE = "message";
	public static final String PAYLOAD_ATTRIBUTE_ACTION = "action";
	public static final String PAYLOAD_ATTRIBUTE_RECIPIENT = "recipient";
	public static final String PAYLOAD_ATTRIBUTE_ACCOUNT = "account";
	public static final String BACKEND_ACTION_ACCIDENT = "new_accident";
	// open FPT API keu
	public static final String OPEN_FPT_API_KEY = "b05ee21f52ec466eb4cf33e99553b219";
	/**
	 * Returns a random message id to uniquely identify a message
	 */
	public static String getUniqueMessageId() {
		// TODO: replace for your own random message ID that the DB generates
		return "m-" + UUID.randomUUID().toString();
	}

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	/**
	 *  Hàm tính khoảng cách giữa 2 diểm dựa trên tọa độ
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
		double earthRadius = 6371000; //meters
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
						Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		float dist = (float) (earthRadius * c);

		return dist;
	}
}
