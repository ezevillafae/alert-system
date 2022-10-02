package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicRepository;
import java.util.Collection;

public class TopicFinder {

  private final TopicRepository topicRepository;

  public TopicFinder(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  public Collection<Topic> findAll() {
    return topicRepository.findAll();
  }

  public Topic find(Long idTopic) {
    return this.topicRepository.find(idTopic);
  }

}
