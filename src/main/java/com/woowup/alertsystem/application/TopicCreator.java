package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicRepository;
import com.woowup.alertsystem.domain.topic.TopicSubscriptionsRepository;

public class TopicCreator {

  private final TopicSubscriptionsRepository topicSubscriptionsRepository;
  private final TopicRepository topicRepository;

  public TopicCreator(TopicSubscriptionsRepository topicSubscriptionsRepository,
      TopicRepository topicRepository) {
    this.topicSubscriptionsRepository = topicSubscriptionsRepository;
    this.topicRepository = topicRepository;
  }

  public void create(Topic topic) {

    topicRepository.save(topic);
    topicSubscriptionsRepository.addTopic(topic.getTopicId());
  }

}
