package com.woowup.alertsystem.application;

import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserAlreadyExistsException;
import com.woowup.alertsystem.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRegister {

  private final UserRepository userRepository;

  public UserRegister(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void register(User user) {
    if(userRepository.exists(user.getUserName())){
      throw new UserAlreadyExistsException("User is already exists");
    }
    userRepository.save(user);
  }

}
