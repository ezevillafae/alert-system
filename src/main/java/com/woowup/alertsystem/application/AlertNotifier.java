package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.alert.AlertNotifierStrategy;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.user.User;
import java.time.LocalDateTime;


public class AlertNotifier {

  public AlertNotifier() {
  }

  public void sendAlert(Topic topic, LocalDateTime expiration, AlertNotifierStrategy strategy) {
    strategy.sendAlert(topic, expiration);
  }

  public void sendAlert(Topic topic, LocalDateTime expiration, User user, AlertNotifierStrategy strategy) {
    strategy.sendAlert(topic, expiration, user);
  }

}
