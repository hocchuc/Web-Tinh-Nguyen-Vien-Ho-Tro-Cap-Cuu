package com.emc.emergency.util;

import com.emc.emergency.data.model.User;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	public static final String BACKEND_ACTION_MESSAGE = "MESSAGE";

	// For the app common payload message attributes (android - xmpp server)
	public static final String PAYLOAD_ATTRIBUTE_MESSAGE = "message";
	public static final String PAYLOAD_ATTRIBUTE_ACTION = "action";
	public static final String PAYLOAD_ATTRIBUTE_RECIPIENT = "recipient";
	public static final String PAYLOAD_ATTRIBUTE_ACCOUNT = "account";
	public static final String BACKEND_ACTION_ACCIDENT = "new_accident";
	public static final String BACKEND_FAKE_ACCIDENT = "fake_accident";
	public static final String BACKEND_DONE_ACCIDENT = "done_accident";
	public static final String BACKEND_ACTION_LOCK_USER = "lock_user";

	// open FPT API key
	public static final String OPEN_FPT_API_KEY = "b05ee21f52ec466eb4cf33e99553b219";
	public static final String GOOGLE_MAP_API_KEY = "AIzaSyCh58JmhtRQEX4RkKjJjOcI1-AAgTix4Zs";

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
	public static Double distFrom(Double lat1, Double lng1, Double lat2, Double lng2) {
		Double earthRadius = 6371000.0; //meters
		Double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
						Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double dist =  (earthRadius * c);

		return dist;
	}

	public static <E> Collection<E> makeCollection(Iterable<E> iter) {
		Collection<E> list = new ArrayList<E>();
		for (E item : iter) {
			list.add(item);
		}
		return list;
	}

	public static List<User> makeUserCollection(Iterable<User> all) {
		List<User> list = new ArrayList<User>();
		for (User item : all) {
			list.add(item);
		}
		return list;
	}
}
