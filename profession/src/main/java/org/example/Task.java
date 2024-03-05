package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
  @NonNull
  String name;
  @NonNull
  int hourCount;
  @NonNull
  boolean isDone = false;

  public Task(@NonNull String name) {
    this.name = name;
  }
}
