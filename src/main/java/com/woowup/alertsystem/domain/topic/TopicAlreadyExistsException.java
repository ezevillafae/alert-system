package com.woowup.alertsystem.domain.topic;

public class TopicAlreadyExistsException extends RuntimeException {

  public TopicAlreadyExistsException(String message) {
    super(message);
  }
}
