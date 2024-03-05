package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
public class Programmer extends Employee {
  private static final int MIN_RATE = 1_500;
  private static final int MAX_RATE = 2_000;
  protected Task task;

  public Programmer(@NonNull String name, @NonNull String surname, @NonNull String patronymic,
                    @NonNull Gender gender) {
    super(name, surname, patronymic, gender);
  }
  @Override
  public void setRate(int rate) {
    if (rate >= MIN_RATE && rate <= MAX_RATE) {
      this.rate = rate;
      return;
    }
    throw new RuntimeException("Ставка не входит в диапазон");

  }

  protected void setDoneTask(Task task) {
    task.setDone(true);
    System.out.printf("Задача: \"%s\" - выполнена", task.getName());
  }
}
