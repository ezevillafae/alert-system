package com.woowup.alertsystem.aplication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.woowup.alertsystem.application.InformativeAlertNotifier;
import com.woowup.alertsystem.common.LocalDateTimeMother;
import com.woowup.alertsystem.common.TopicMother;
import com.woowup.alertsystem.common.UserMother;
import com.woowup.alertsystem.domain.topic.SubscriptionException;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicSubscriptionsRepository;
import com.woowup.alertsystem.domain.user.User;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformativeAlertNotifierTest {

  private InformativeAlertNotifier informativeAlertNotifier;

  private TopicSubscriptionsRepository topicSubscriptionsRepository;

  @BeforeEach
  void setUp() {
    this.topicSubscriptionsRepository = mock(TopicSubscriptionsRepository.class);
    this.informativeAlertNotifier = new InformativeAlertNotifier(topicSubscriptionsRepository);
  }

  @Test
  void should_users_receive_alert() {
    List<User> subscripts = UserMother.randoms();
    given(topicSubscriptionsRepository.findSubscripts(any())).willReturn(subscripts);

    informativeAlertNotifier.sendAlert(TopicMother.random(), LocalDateTimeMother.futureDateTime());

    assertAllUsersHaveAlert(subscripts);
  }

  @Test
  void should_user_receive_alert() {
    User userSubscript = UserMother.random();
    Topic topic = TopicMother.random();
    given(topicSubscriptionsRepository.isSubscribed(topic.getTopicId(), userSubscript)).willReturn(
        true);

    informativeAlertNotifier.sendAlert(topic, LocalDateTimeMother.futureDateTime(), userSubscript);

    assertUserHaveAlert(userSubscript);
  }

  @Test
  void should_not_receive_alert_when_user_is_not_subscribed() {
    User userSubscript = UserMother.random();
    Topic topic = TopicMother.random();
    given(topicSubscriptionsRepository.isSubscribed(topic.getTopicId(), userSubscript)).willReturn(
        false);

    assertThrows(SubscriptionException.class,
        () -> informativeAlertNotifier.sendAlert(topic, LocalDateTimeMother.futureDateTime(),
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
