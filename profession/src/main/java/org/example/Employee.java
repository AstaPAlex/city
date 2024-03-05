package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends Human {
  @NonNull
  int rate;

  public Employee(@NonNull String name, @NonNull String surname,
                    @NonNull String patronymic, @NonNull Gender gender) {
    super(name, surname, patronymic, gender);
  }
}
