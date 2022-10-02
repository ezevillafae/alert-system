package com.woowup.alertsystem.domain.user;

import com.woowup.alertsystem.domain.alert.Alert;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class User {

  private String userName;
  private final Map<Long, Alert> alerts;

  public User(String userName) {
    this.userName = userName;
    this.alerts = new HashMap<>();
  }

  public void update(Alert alert) {
    if(alert.isNotExpired()) {
      alerts.put(alert.getAlertId(), alert);
    }
  }

  public void readAlert(Long alertId) {
    if(alerts.get(alertId) != null) {
      alerts.get(alertId).markAsRead();
    }
  }

  public Collection<Alert> getAlerts() {
    return alerts.values();
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(getUserName()).append(" ");

    alerts.forEach((aLong, alert) -> sb.append(alert.toString()));

    return sb.toString();
  }
}
