package com.woowup.alertsystem.common;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class LocalDateTimeMother {

  public static LocalDateTime pastDateTime() {
    return LocalDateTime.of(1900, 2,14, 1, 1);
  }

  public static LocalDateTime futureDateTime(int days) {
    return LocalDateTime.now().plus(days, ChronoUnit.DAYS);
  }

  public static LocalDateTime futureDateTime() {
    return LocalDateTime.now().plus(1, ChronoUnit.DAYS);
  }

  public static LocalDateTime buildFutureDateTime(int year) {
    return LocalDateTime.of(year, 12, 12,12,12);
  }

}
