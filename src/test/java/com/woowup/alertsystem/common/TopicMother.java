package com.woowup.alertsystem.common;

import com.woowup.alertsystem.domain.topic.Topic;
import org.apache.commons.lang.RandomStringUtils;

public class TopicMother {

  public static Topic random() {
    return new Topic(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5));
  }

  public static Topic build(Long id) {
    return new Topic(100L,"Topic", "Topic description");
  }

}
