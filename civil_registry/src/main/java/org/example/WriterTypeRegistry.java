package org.example;

import java.time.LocalDate;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriterTypeRegistry {
  @NonNull
    LocalDate date;
  @NonNull
  TypeRegistry typeRegistry;
  List<Citizen> list;

  @Override
   public String toString() {
    return "WriterTypeRegistry{"
      + "date=" + date
      + ", typeRegistry=" + typeRegistry
      + ", list=" + list
      + '}';
  }
}
