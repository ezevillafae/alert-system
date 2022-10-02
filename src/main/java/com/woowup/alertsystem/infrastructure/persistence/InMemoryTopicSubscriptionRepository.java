package com.woowup.alertsystem.infrastructure.persistence;

import com.woowup.alertsystem.domain.subcription.TopicSubscriptionsRepository;
import com.woowup.alertsystem.domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTopicSubscriptionRepository implements TopicSubscriptionsRepository {

  private final Map<Long, List<User>> subscriptions;

  public InMemoryTopicSubscriptionRepository() {
    this.subscriptions = new HashMap<>();
  }

  @Override
  public void addTopic(Long idTopic) {
    this.subscriptions.put(idTopic, new ArrayList<>());
  }

  @Override
  public void subscribe(Long idTopic, User user) {
    this.subscriptions.get(idTopic).add(user);
  }

  @Override
  public void unsubscribe(Long idTopic, User user) {
    this.subscriptions.get(idTopic).remove(user);
  }

  @Override
  public boolean isSubscribed(Long idTopic, User user) {
    return this.subscriptions.get(idTopic).contains(user);
  }

  @Override
  public List<User> findSubscripts(Long idTopic) {
    return this.subscriptions.get(idTopic);
  }


}
