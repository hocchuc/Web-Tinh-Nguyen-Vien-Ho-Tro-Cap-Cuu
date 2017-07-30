package com.emc.emergency.data.bean;

/**
 * Created by hocan on 29-Jul-17.
 */
public class Notice {
  private String content;

  public Notice() {
  }

  public Notice(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
