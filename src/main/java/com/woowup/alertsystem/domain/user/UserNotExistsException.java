package com.woowup.alertsystem.domain.user;

public class UserNotExistsException extends RuntimeException {

  public UserNotExistsException(String message) {
    super(message);
  }
}
