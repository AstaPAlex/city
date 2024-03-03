package org.example;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Citizen extends Human {
  @NonNull
  FamilyStatus familyStatus;
  Human human;
  Citizen citizen;

  public Citizen(@NonNull String name, @NonNull String surname, @NonNull String patronymic,
               @NonNull Gender gender, FamilyStatus familyStatus) {
    super(name, surname, patronymic, gender);
    this.familyStatus = familyStatus;
  }

  public Citizen(@NonNull String name, @NonNull String surname, @NonNull String patronymic,
               @NonNull Gender gender, @NonNull FamilyStatus familyStatus, Citizen citizen) {
    this(name, surname, patronymic, gender, familyStatus);
    this.citizen = citizen;
  }

  public Citizen(@NonNull String name, @NonNull String surname, @NonNull String patronymic,
               @NonNull Gender gender, Citizen citizen) {
    super(name, surname, patronymic, gender);
    this.citizen = citizen;
  }

  public Citizen(Human human) {
    super(human.getName(), human.getSurname(), human.getPatronymic(), human.getGender());
  }
}
