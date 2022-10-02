package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserNotExistsException;
import com.woowup.alertsystem.domain.user.UserRepository;

public class UserFinder {

  private final UserRepository userRepository;

  public UserFinder(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User find(String username) {
    return userRepository.find(username).orElseThrow(() -> new UserNotExistsException("User not exists"));
  }
}
