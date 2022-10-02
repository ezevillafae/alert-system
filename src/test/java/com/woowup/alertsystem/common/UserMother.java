package com.woowup.alertsystem.common;

import com.woowup.alertsystem.domain.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang.RandomStringUtils;

public class UserMother {

  public static User random() {
    return new User(RandomStringUtils.randomAlphabetic(5));
  }

  public static List<User> randoms() {
    List<User> users = new ArrayList<>();
    IntStream
        .range(0, 5)
        .forEach(i -> users.add(random()));
    return users;
  }

  public static User userWithAlerts() {
    User user = new User(RandomStringUtils.randomAlphabetic(5));

    for (int i = 0; i < 4; i++) {
      user.update(AlertMother.notReadAndNotExpired());
    }
    return user;
  }

  public static User userWithDisorderedAlerts() {
    User user = new User(RandomStringUtils.randomAlphabetic(5));

    for (var alert: AlertMother.alertsInDescendingOrder()) {
      user.update(alert);
    }
    return user;
  }

  public static User userWithReadAlerts() {
    User user = new User(RandomStringUtils.randomAlphabetic(5));

    for (int i = 0; i < 5; i++) {
      user.update(AlertMother.read());
    }

    return user;
  }

  public static List<User> withAlertsDifferentTopic() {
    List<User> users = randoms();

    users.forEach(user -> user.update(AlertMother.notExpired()));

    User user = UserMother.random();
    user.update(AlertMother.build(100L));

    users.add(user);
    return users;
  }


}
