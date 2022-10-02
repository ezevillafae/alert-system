package com.woowup.alertsystem.domain.topic;

import java.util.Objects;

public class Topic {

  private static Long lastId = 0L;
  private Long topicId;
  private String title;
  private String description;

  public Topic(String title, String description) {
    this.topicId = getNextId();
    this.title = title;
    this.description = description;
  }

  public Topic(Long topicId, String title, String description) {
    this.topicId = topicId;
    this.title = title;
    this.description = description;
  }

  public static Long getLastId() {
    return lastId;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  private Long getNextId() {
    return lastId++;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Topic topic = (Topic) o;
    return Objects.equals(topicId, topic.topicId) && Objects.equals(title,
        topic.title) && Objects.equals(description, topic.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topicId, title, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Topic ID: ").append(this.topicId);
    sb.append(", Topic Title: ").append(this.title);
    sb.append(", Topic Description: ").append(this.description);

    return sb.toString();
  }


}
