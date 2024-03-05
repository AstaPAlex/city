package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Programmer {

  int rate;
  static final int MIN_RATE = 1_500;
  static final int MAX_RATE = 2_000;
  Task task;

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
