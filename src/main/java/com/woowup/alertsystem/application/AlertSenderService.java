package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.alert.AlertNotifier;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.user.User;
import java.time.LocalDateTime;


public class AlertSenderService {

  public AlertSenderService() {
  }

  public void sendAlert(Topic topic, LocalDateTime expiration, AlertNotifier alertNotifier) {
    alertNotifier.sendAlert(topic, expiration);
  }

  public void sendAlert(Topic topic, LocalDateTime expiration, User user, AlertNotifier alertNotifier) {
    alertNotifier.sendAlert(topic, expiration, user);
  }

}
