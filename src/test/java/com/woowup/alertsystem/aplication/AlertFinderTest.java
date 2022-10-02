package com.woowup.alertsystem.aplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.woowup.alertsystem.common.UserMother;
import com.woowup.alertsystem.application.AlertFinder;
import com.woowup.alertsystem.domain.alert.Alert;
import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlertFinderTest {

  private AlertFinder alertFinder;

  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    this.userRepository = mock(UserRepository.class);
    this.alertFinder = new AlertFinder(userRepository);
  }

  @Test
  void should_find_alerts_not_read_and_not_expired() {
    User userWithAlert = UserMother.userWithAlerts();
    given(userRepository.find(userWithAlert.getUserName())).willReturn(Optional.of(userWithAlert));

    List<Alert> actual = alertFinder.findAlerts(userWithAlert.getUserName());

    assertAllAlertsAreNotReadAndNotExpired(actual);
  }

  @Test
  void should_not_return_read_alerts() {
    User userWithAlert = UserMother.userWithReadAlerts();
    given(userRepository.find(userWithAlert.getUserName())).willReturn(Optional.of(userWithAlert));

    List<Alert> actual = alertFinder.findAlerts(userWithAlert.getUserName());

    assertEquals(0, actual.size());
  }

  @Test
  void should_return_alert_with_when_have_different_topic() {
    List<User> users = UserMother.withAlertsDifferentTopic();
    given(userRepository.findAll()).willReturn(users);

    List<Alert> alerts = alertFinder.findAlertByTopic(100L);

    assertEquals(1, alerts.size());
    assertAllAlertsAreNotExpired(alerts);
  }

  void assertAllAlertsAreNotExpired(List<Alert> alerts) {
    for (Alert alert : alerts) {
      assertAlertIsNotExpired(alert);
    }
  }

  void assertAllAlertsAreNotReadAndNotExpired(List<Alert> alerts) {
    for (Alert alert : alerts) {
      assertAlertIsNotRead(alert);
      assertAlertIsNotExpired(alert);
    }
  }

  void assertAlertIsNotRead(Alert alert) {
    assertTrue(alert.isNotRead());
  }

  void assertAlertIsNotExpired(Alert alert) {
    assertTrue(alert.isNotExpired());
  }

}
