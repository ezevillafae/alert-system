package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.alert.Alert;
import com.woowup.alertsystem.domain.alert.AlertNotifier;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;
import java.time.LocalDateTime;

public class UrgentAlertNotifier implements AlertNotifier {

  private final UserRepository userRepository;

  public UrgentAlertNotifier(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public void sendAlert(Topic topic, LocalDateTime expiration) {
    userRepository
        .findAll()
        .forEach(user -> user.update(new Alert(false, topic, expiration)));
  }

  @Override
  public void sendAlert(Topic topic, LocalDateTime expiration, User user) {
    User userSaved = userRepository
        .find(user.getUserName())
        .orElseThrow(RuntimeException::new);

    userSaved.update(new Alert(true, topic, expiration));
  }
}
