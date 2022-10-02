package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;

public class AlertReader {

  private final UserRepository userRepository;

  public AlertReader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void readAlert(String username, Long alertId) {
    User user = userRepository.find(username).orElseThrow();

    user.readAlert(alertId);
  }

}
