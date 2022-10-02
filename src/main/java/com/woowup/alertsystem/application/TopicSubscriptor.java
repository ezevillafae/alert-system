package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.subcription.TopicSubscriptionsRepository;
import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;

public class TopicSubscriptor {

  private final TopicSubscriptionsRepository topicSubscriptionsRepository;
  private final UserRepository userRepository;

  public TopicSubscriptor(TopicSubscriptionsRepository topicSubscriptionsRepository,
      UserRepository userRepository) {
    this.topicSubscriptionsRepository = topicSubscriptionsRepository;
    this.userRepository = userRepository;
  }

  public void subscribe(String userName, Long idTopic) {
    User userSaved = userRepository.find(userName).orElseThrow(RuntimeException::new);

    topicSubscriptionsRepository.subscribe(idTopic, userSaved);
  }

}
