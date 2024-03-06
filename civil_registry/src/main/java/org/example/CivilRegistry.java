package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CivilRegistry {
  String name;
  MultiValuedMap<LocalDate, WriterTypeRegistry> marriageMap = new ArrayListValuedHashMap<>();
  MultiValuedMap<LocalDate, WriterTypeRegistry> divorceMap = new ArrayListValuedHashMap<>();
  MultiValuedMap<LocalDate, WriterTypeRegistry> birthMap = new ArrayListValuedHashMap<>();

  public CivilRegistry(String name) {
    this.name = name;
  }

  /**
   * 3.4.1 Рождение ребенка - передается новорожденный, отец, мать + дата регистрации рождения.
   * Создается запись гражданского действия за дату регистрации.
   */
  public void setRecordBirthChildRegistry(Citizen child, Citizen father,
                                          Citizen mother, LocalDate date) {

    birthMap.put(date, new WriterTypeRegistry(date, TypeRegistry.BIRTH_REGISTRATION,
            List.of(child, father, mother)));

  }

  /**
   * 3.4.2 Регистрация свадьбы - передаются мужчина и женщина + дата регистрации свадьбы.
   * Меняется семейный статус у мужчины и женщины.
   * Заносится в список свадьб. Создается запись гражданского действия за дату регистрации.
   */
  public void setRecordMarriageRegistry(Citizen man, Citizen woman, LocalDate date) {
    man.setFamilyStatus(FamilyStatus.MARRIED);
    man.setCitizen(woman);
    woman.setFamilyStatus(FamilyStatus.MARRIED);
    woman.setCitizen(man);

    marriageMap.put(date, new WriterTypeRegistry(date, TypeRegistry.MARRIAGE_REGISTRATION,
            List.of(man, woman)));
  }

  /**
   * 3.4.3 Регистрация развода - передаются мужчина и женщина + дата регистрации развода.
   * Меняется семейный статус у мужчины и женщины. Заносится в список разводов.
   * Создается запись гражданского действия за дату регистрации.
   */
  public void setRecordDivorceRegistry(Citizen man, Citizen woman, LocalDate date) {
    man.setFamilyStatus(FamilyStatus.DIVORSED);
    man.setCitizen(null);
    woman.setFamilyStatus(FamilyStatus.DIVORSED);
    woman.setCitizen(null);

    divorceMap.put(date, new WriterTypeRegistry(date, TypeRegistry.DIVORCE_REGISTRATION,
            List.of(man, woman)));
  }

  /**
   * 3.4.4 Создать метод получения статистики за дату, формат печати в консоль:
   * "Статистика по ЗАГС: [имя загса]
   * Дата 20/02/2023: количество свадеб - 1, количество разводов - 2, количество рождений - 5"
   */
  public void report() {
    System.out.printf("Статистика по ЗАГС: %s\n", name);

    Map<LocalDate, Integer> countMarriageList = getCountList(sort(marriageMap));
    Map<LocalDate, Integer> countDivorceList = getCountList(sort(divorceMap));
    Map<LocalDate, Integer> countBirthList = getCountList(sort(birthMap));

    Set<LocalDate> dates = new LinkedHashSet<>();
    dates.addAll(countMarriageList.keySet());
    dates.addAll(countDivorceList.keySet());
    dates.addAll(countBirthList.keySet());
    dates.stream()
            .forEach(workDay ->
                    System.out.printf("Дата %s: количество свадеб - %s,"
                            + " количество разводов - %s, количество рождений - %s\n",
                            workDay.toString(),
                            countMarriageList.get(workDay) == null ? 0 : countMarriageList.get(workDay),
                            countDivorceList.get(workDay) == null ? 0 : countDivorceList.get(workDay),
                            countBirthList.get(workDay) == null ? 0 : countBirthList.get(workDay)));
  }

  private List<Map.Entry<LocalDate, WriterTypeRegistry>> sort(MultiValuedMap<LocalDate, WriterTypeRegistry> map) {
    return map.entries().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .collect(Collectors.toList());
  }

  private Map<LocalDate, Integer> getCountList(List<Map.Entry<LocalDate, WriterTypeRegistry>> list) {
    LocalDate localDatePrev = null;
    Map<LocalDate, Integer> map = new LinkedHashMap<>();
    for (Map.Entry<LocalDate, WriterTypeRegistry> entry : list) {
      if (localDatePrev != entry.getKey()) {
        long count = list.stream().filter(el1 -> el1.getKey().equals(entry.getKey())).count();
        map.put(entry.getKey(), Integer.valueOf(Long.toString(count)));
        localDatePrev = entry.getKey();
      }
    }
    return map;
  }
}