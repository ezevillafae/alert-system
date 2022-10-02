package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.alert.Alert;
import com.woowup.alertsystem.domain.alert.AlertNotifierStrategy;
import com.woowup.alertsystem.domain.topic.SubscriptionException;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicSubscriptionsRepository;
import com.woowup.alertsystem.domain.user.User;
import java.time.LocalDateTime;

public  class InformativeAlertNotifier implements AlertNotifierStrategy {

  private final TopicSubscriptionsRepository topicSubscriptionsRepository;

  public InformativeAlertNotifier(TopicSubscriptionsRepository topicSubscriptionsRepository) {
    this.topicSubscriptionsRepository = topicSubscriptionsRepository;
  }

  @Override
  public void sendAlert(Topic topic, LocalDateTime expiration) {
    topicSubscriptionsRepository
        .findSubscripts(topic.getTopicId())
        .forEach(user -> user.update(new Alert(false, topic, expiration)));
  }

  @Override
  public void sendAlert(Topic topic, LocalDateTime expiration, User user) {
    if(!topicSubscriptionsRepository.isSubscribed(topic.getTopicId(), user)) {
      throw new SubscriptionException("the user must be subscribed to the topic");
    }
    user.update(new Alert(true, topic, expiration));
  }
}