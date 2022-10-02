package com.woowup.alertsystem.domain.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Collection<User> findAll();

  Optional<User> find(String userName);

  boolean exists(String userName);

}
