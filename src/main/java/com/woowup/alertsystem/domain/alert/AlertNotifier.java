package com.woowup.alertsystem.domain.alert;

import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.user.User;
import java.time.LocalDateTime;

public interface AlertNotifier {

  void sendAlert(Topic topic, LocalDateTime expiration);
  void sendAlert(Topic topic, LocalDateTime expiration, User user);

}