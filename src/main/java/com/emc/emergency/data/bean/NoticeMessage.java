package com.emc.emergency.data.bean;

/**
 * Created by hocan on 29-Jul-17.
 */
public class NoticeMessage {

  private String name;

  public NoticeMessage() {
  }

  public NoticeMessage(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
