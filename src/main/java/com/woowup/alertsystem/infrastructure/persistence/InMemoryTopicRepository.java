package com.woowup.alertsystem.infrastructure.persistence;

import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicRepository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTopicRepository implements TopicRepository {

  private final Map<Long, Topic> topics;

  public InMemoryTopicRepository() {
    this.topics = new HashMap<>();
  }

  @Override
  public void save(Topic topic) {
    this.topics.put(topic.getTopicId(), topic);
  }

  @Override
  public Topic find(Long idTopic) {
    return this.topics.get(idTopic);
  }

  @Override
  public Collection<Topic> findAll() {
    return this.topics.values();
  }
}
