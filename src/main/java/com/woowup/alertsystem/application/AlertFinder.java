package com.woowup.alertsystem.application;

import static java.util.stream.Collectors.toList;

import com.woowup.alertsystem.domain.alert.Alert;
import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlertFinder {

  private final UserRepository userRepository;

  public AlertFinder(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public List<Alert> findAlerts(String username) {
    User userSaved = userRepository.find(username).orElseThrow(RuntimeException::new);

    return userSaved.getAlerts()
        .stream()
        .filter(Alert::isNotExpired)
        .filter(Alert::isNotRead)
        .sorted(Alert::compareTo)
        .collect(toList());
  }

  public List<Alert> findAlertByTopic(Long idTopic) {
    Collection<User> users = userRepository.findAll();

    List<Alert> alerts = new ArrayList<>();

    for (var user: users) {
      user
          .getAlerts()
          .stream()
          .filter(Alert::isNotExpired)
          .filter(alert -> alert.getTopic().getTopicId().equals(idTopic))
          .forEach(alerts::add);
    }

    Collections.sort(alerts);

    return alerts;
  }



}
