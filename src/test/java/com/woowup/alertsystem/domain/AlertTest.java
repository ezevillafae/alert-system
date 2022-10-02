package com.woowup.alertsystem.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.woowup.alertsystem.common.AlertMother;
import com.woowup.alertsystem.domain.alert.Alert;
import org.junit.jupiter.api.Test;

class AlertTest {

  @Test
  void should_be_expired() {
    Alert alert = AlertMother.expired();

    boolean actual = alert.isNotExpired();

    assertFalse(actual);
  }

  @Test
  void should_be_not_expired() {
    Alert alert = AlertMother.notExpired();

    boolean actual = alert.isNotExpired();

    assertTrue(actual);
  }

}
