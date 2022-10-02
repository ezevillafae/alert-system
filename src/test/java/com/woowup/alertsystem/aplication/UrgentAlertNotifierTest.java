package com.woowup.alertsystem.aplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.woowup.alertsystem.common.LocalDateTimeMother;
import com.woowup.alertsystem.common.TopicMother;
import com.woowup.alertsystem.common.UserMother;
import com.woowup.alertsystem.application.UrgentAlertNotifier;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UrgentAlertNotifierTest {

  private UrgentAlertNotifier urgentAlertNotifier;

  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    this.userRepository = mock(UserRepository.class);
    this.urgentAlertNotifier = new UrgentAlertNotifier(userRepository);
  }

  @Test
  void should_users_receive_alert() {
    List<User> usersSaved = UserMother.randoms();
    given(userRepository.findAll()).willReturn(usersSaved);

    urgentAlertNotifier.sendAlert(TopicMother.random(), LocalDateTimeMother.futureDateTime());

    assertAllUsersHaveAlert(usersSaved);
  }

  @Test
  void should_user_receive_alert() {
    User userSubscript = UserMother.random();
    Topic topic = TopicMother.random();
    given(userRepository.find(userSubscript.getUserName())).willReturn(Optional.of(userSubscript));

    urgentAlertNotifier.sendAlert(topic, LocalDateTimeMother.futureDateTime(), userSubscript);

    assertUserHaveAlert(userSubscript);
  }

  @Test
  void should_throws_exception_when_user_is_not_registered() {
    User userSubscript = UserMother.random();
    Topic topic = TopicMother.random();
    given(userRepository.find(userSubscript.getUserName())).willReturn(Optional.empty());

    assertThrows(RuntimeException.class,
        () -> urgentAlertNotifier.sendAlert(topic, LocalDateTimeMother.futureDateTime(),
            userSubscript));
  }

  void assertUserDontHaveAlert(User user) {
    assertEquals(0, user.getAlerts().size());
  }

  void assertUserHaveAlert(User user) {
    assertEquals(1, user.getAlerts().size());
  }

  void assertAllUsersHaveAlert(List<User> users) {
    for (User user : users) {
      assertUserHaveAlert(user);
    }
  }
}
