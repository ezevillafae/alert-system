package com.woowup.alertsystem.domain.topic;

import com.woowup.alertsystem.domain.user.User;
import java.util.List;

public interface TopicSubscriptionsRepository {

  void addTopic(Long idTopic);
  void subscribe(Long idTopic, User user);
  void unsubscribe(Long idTopic, User user);
  boolean isSubscribed(Long idTopic, User user);
  List<User> findSubscripts(Long idTopic);

}
