package com.woowup.alertsystem.aplication;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.woowup.alertsystem.application.TopicCreator;
import com.woowup.alertsystem.common.TopicMother;
import com.woowup.alertsystem.domain.subcription.TopicSubscriptionsRepository;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicAlreadyExistsException;
import com.woowup.alertsystem.domain.topic.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopicCreatorTest {

  private TopicCreator topicCreator;

  private TopicSubscriptionsRepository topicSubscriptionsRepository;
  private TopicRepository topicRepository;
  @BeforeEach
  void setUp() {
    this.topicSubscriptionsRepository = mock(TopicSubscriptionsRepository.class);
    this.topicRepository = mock(TopicRepository.class);
    this.topicCreator = new TopicCreator(topicSubscriptionsRepository, topicRepository);
  }

  @Test
  void should_throws_exception_when_topic_exists() {
    Topic topic = TopicMother.random();
    given(topicRepository.exists(topic.getTopicId())).willReturn(true);

    assertThrows(TopicAlreadyExistsException.class, () -> topicCreator.create(topic));
    verify(topicSubscriptionsRepository, times(0)).addTopic(topic.getTopicId());
    verify(topicRepository, times(0)).save(topic);
  }

  @Test
  void should_save_topic_when_exists() {
    Topic topic = TopicMother.random();
    given(topicRepository.exists(topic.getTopicId())).willReturn(false);

    topicCreator.create(topic);

    verify(topicSubscriptionsRepository, times(1)).addTopic(topic.getTopicId());
    verify(topicRepository, times(1)).save(topic);
  }

}
