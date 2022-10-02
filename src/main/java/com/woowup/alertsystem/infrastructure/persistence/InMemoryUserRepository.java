package com.woowup.alertsystem.infrastructure.persistence;

import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

  private final Map<String, User> users;

  public InMemoryUserRepository() {
    this.users = new HashMap<>();
  }

  @Override
  public void save(User user) {
    this.users.put(user.getUserName(), user);
  }

  @Override
  public Collection<User> findAll() {
    return this.users.values();
  }

  @Override
  public Optional<User> find(String userName) {
    return Optional.ofNullable(this.users.get(userName));
  }

  @Override
  public boolean exists(String userName) {
    return this.users.containsKey(userName);
  }
}
