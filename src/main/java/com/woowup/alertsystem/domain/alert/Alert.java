package com.woowup.alertsystem.domain.alert;

import com.woowup.alertsystem.domain.topic.Topic;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alert implements Comparable<Alert> {

  private final Long alertId;
  private static Long lastId = 0L;
  private final boolean specific;
  private boolean read;
  private Topic topic;
  private LocalDateTime expiration;

  public Alert(boolean specific, Topic topic, LocalDateTime expiration) {
    this.alertId = getNextId();
    this.read = false;
    this.specific = specific;
    this.topic = topic;
    this.expiration = expiration;
  }

  public boolean isSingleAlert() {
    return specific;
  }

  public boolean isNotRead() {
    return !read;
  }

  public void markAsRead() {
    this.read = true;
  }

  public void markAsNotRead() {
    this.read = false;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public LocalDateTime getExpiration() {
    return expiration;
  }

  public void setExpiration(LocalDateTime expiration) {
    this.expiration = expiration;
  }

  public boolean isNotExpired() {
    return this.getExpiration().isAfter(LocalDateTime.now());
  }

  public Long getAlertId() {
    return alertId;
  }

  public static Long getLastId() {
    return lastId;
  }

  private static Long getNextId() {
    return lastId++;
  }


  @Override
  public int compareTo(Alert otherAlert) {
    return this.getExpiration().compareTo(otherAlert.getExpiration());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Alert ID: ").append(this.alertId);
    sb.append(", specific: ").append(this.specific);
    sb.append(", read: ").append(this.read);
    sb.append(", expiration, ").append(expiration.format(DateTimeFormatter.ISO_DATE_TIME));
    sb.append(", topic: " ).append(topic.toString());

    return sb.toString();
  }
}
