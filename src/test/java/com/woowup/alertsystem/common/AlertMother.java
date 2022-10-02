package com.woowup.alertsystem.common;

import com.woowup.alertsystem.domain.alert.Alert;
import com.woowup.alertsystem.domain.topic.Topic;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AlertMother {

  public static Alert expired() {
    return new Alert(false, TopicMother.random(), LocalDateTimeMother.pastDateTime());
  }

  public static Alert notExpired() {
    return new Alert(false, TopicMother.random(), LocalDateTimeMother.futureDateTime());
  }

  public static Alert build(Long topicId) {
    return new Alert(false, TopicMother.build(topicId), LocalDateTimeMother.buildFutureDateTime(3000));
  }

  public static Alert notReadAndNotExpired() {
    return new Alert(false, TopicMother.random(), LocalDateTimeMother.futureDateTime());
  }

  public static Alert read() {
    Alert alert = new Alert(false, TopicMother.random(), LocalDateTimeMother.futureDateTime());
    alert.markAsRead();
    return alert;
  }

  public static List<Alert> alertsNotReadAndNotExpired() {
    List<Alert> alerts = new ArrayList<>();
    IntStream
        .range(0, 5)
        .forEach(i -> alerts.add(notReadAndNotExpired()));

    return alerts;
  }

  public static List<Alert> alertsInDescendingOrder() {
    List<Alert> alerts = new ArrayList<>();
    alerts.add(new Alert(false,TopicMother.random(), LocalDateTimeMother.futureDateTime(3)));
    alerts.add(new Alert(false,TopicMother.random(), LocalDateTimeMother.futureDateTime(2)));
    alerts.add(new Alert(false,TopicMother.random(), LocalDateTimeMother.futureDateTime(1)));
    return alerts;
  }

  public static List<Alert> withSameTopic() {
    Topic topic = TopicMother.random();
    List<Alert> alerts = new ArrayList<>();

    alerts.add(new Alert(false,topic, LocalDateTimeMother.futureDateTime(3)));
    alerts.add(new Alert(false,topic, LocalDateTimeMother.futureDateTime(2)));
    alerts.add(new Alert(false,topic, LocalDateTimeMother.futureDateTime(1)));

    return alerts;
  }

}
