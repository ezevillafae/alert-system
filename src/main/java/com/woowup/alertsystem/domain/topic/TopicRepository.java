package com.woowup.alertsystem.domain.topic;

import java.util.Collection;

public interface TopicRepository {

  void save(Topic topic);

  Topic find(Long idTopic);

  Collection<Topic> findAll();

}
