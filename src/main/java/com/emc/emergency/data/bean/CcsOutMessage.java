package com.emc.emergency.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

/**
 * Represents an outgoing message to FCM CCS
 */
public class CcsOutMessage {

	// Sender registration ID
	private String to;
	// Condition that determines the message target
	private String condition;
	// Unique id for this message
	private String messageId;
	// Identifies a group of messages
	private String collapseKey;
	// Priority of the message
	private String priority;
	// Flag to wake client devices
	private Boolean contentAvailable;
	// Flag to delay the delivery while device is idle
	private Boolean delayWhileIdle;
	// Time to live
	private Integer timeToLive;
	// Flag to request confirmation of message delivery
	private Boolean deliveryReceiptRequested;
	// Test request without sending a message
	private Boolean dryRun;
	// Payload data. A String in JSON format
	private Map<String, String> dataPayload;
	// Payload notification. A String in JSON format
	private Map<String, String> notificationPayload;

	public CcsOutMessage(String to, String messageId, Map<String, String> dataPayload) {
		this.to = to;
		this.messageId = messageId;
		this.dataPayload = dataPayload;
	}

	public CcsOutMessage(String to, String messageId, Map<String, String> dataPayload,Map<String, String> notificationPayload ) {
		this.to = to;
		this.messageId = messageId;
		this.dataPayload = dataPayload;
		this.notificationPayload = notificationPayload;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getCollapseKey() {
		return collapseKey;
	}

	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Boolean isContentAvailable() {
		return contentAvailable;
	}

	public void setContentAvailable(Boolean contentAvailable) {
		this.contentAvailable = contentAvailable;
	}

	public Boolean isDelayWhileIdle() {
		return delayWhileIdle;
	}

	public void setDelayWhileIdle(Boolean delayWhileIdle) {
		this.delayWhileIdle = delayWhileIdle;
	}

	public Integer getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	public Boolean isDeliveryReceiptRequested() {
		return deliveryReceiptRequested;
	}

	public void setDeliveryReceiptRequested(Boolean deliveryReceiptRequested) {
		this.deliveryReceiptRequested = deliveryReceiptRequested;
	}

	public Boolean isDryRun() {
		return dryRun;
	}

	public void setDryRun(Boolean dryRun) {
		this.dryRun = dryRun;
	}

	public Map<String, String> getDataPayload() {
		return dataPayload;
	}

	public void setDataPayload(Map<String, String> dataPayload) {
		this.dataPayload = dataPayload;
	}

	public Map<String, String> getNotificationPayload() {
		return notificationPayload;
	}

	public void setNotificationPayload(Map<String, String> notificationPayload) {
		this.notificationPayload = notificationPayload;
	}

}