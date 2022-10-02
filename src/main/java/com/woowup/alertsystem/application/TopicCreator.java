package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicAlreadyExistsException;
import com.woowup.alertsystem.domain.topic.TopicRepository;
import com.woowup.alertsystem.domain.subcription.TopicSubscriptionsRepository;

public class TopicCreator {

  private final TopicSubscriptionsRepository topicSubscriptionsRepository;
  private final TopicRepository topicRepository;

  public TopicCreator(TopicSubscriptionsRepository topicSubscriptionsRepository,
      TopicRepository topicRepository) {
    this.topicSubscriptionsRepository = topicSubscriptionsRepository;
    this.topicRepository = topicRepository;
  }

  public void create(Topic topic) {
    if(topicRepository.exists(topic.getTopicId())) {
      throw new TopicAlreadyExistsException("Topic is already exists");
    }

    topicRepository.save(topic);
    topicSubscriptionsRepository.addTopic(topic.getTopicId());
  }

}
