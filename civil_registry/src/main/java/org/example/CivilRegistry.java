package org.example;

import java.time.LocalDate;
import java.util.*;


public class CivilRegistry {
    private String name;
    private List<WriterTypeRegistry> records = new LinkedList<>();
    private static Map<LocalDate, Integer> marriageList = new HashMap<>();
    private static Map<LocalDate, Integer> divorceList = new HashMap<>();
    private static Map<LocalDate, Integer> birthList = new HashMap<>();
    private static Set<LocalDate> workDays = new TreeSet<>(LocalDate::compareTo);

    public CivilRegistry(String name) {
        this.name = name;
    }

    /**
     * 3.4.1 Рождение ребенка - передается новорожденный, отец, мать + дата регистрации рождения.
     * Создается запись гражданского действия за дату регистрации.
     */
    public void setRecordBirthChildRegistry(Citizen child, Citizen father, Citizen mother, LocalDate date) {
        setElementToMap(birthList, date);
        workDays.add(date);
        records.add(new WriterTypeRegistry(date, TypeRegistry.BIRTH_REGISTRATION, List.of(child, father, mother)));
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
        setElementToMap(marriageList, date);
        workDays.add(date);
        records.add(new WriterTypeRegistry(date, TypeRegistry.MARRIAGE_REGISTRATION, List.of(man, woman)));
    }

    /**
     * 3.4.3 Регистрация развода - передаются мужчина и женщина + дата регистрации развода.
     * Меняется семейный статус у мужчины и женщины. Заносится в список разводов.
     * Создается запись гражданского действия за дату регистрации.
     */
    public void setRecordDivorceRegistry(Citizen man, Citizen woman, LocalDate date) {
        man.setFamilyStatus(FamilyStatus.NOT_WIFE);
        man.setCitizen(null);
        woman.setFamilyStatus(FamilyStatus.NOT_HUSBAND);
        woman.setCitizen(null);
        setElementToMap(divorceList, date);
        workDays.add(date);
        records.add(new WriterTypeRegistry(date, TypeRegistry.DIVORCE_REGISTRATION, List.of(man, woman)));
    }

    /**
     * 3.4.4 Создать метод получения статистики за дату, формат печати в консоль:
     * "Статистика по ЗАГС: [имя загса]
     * Дата 20/02/2023: количество свадеб - 1, количество разводов - 2, количество рождений - 5"
     */
    public void report() {
        System.out.printf("Статистика по ЗАГС: %s\n", name);
        int countMarriage = 0;
        int countDivorce = 0;
        int countBirth = 0;
        for (LocalDate workDay : workDays) {
            if (marriageList.containsKey(workDay)) {
                countMarriage = marriageList.get(workDay);
            }
            if (divorceList.containsKey(workDay)) {
                countDivorce = divorceList.get(workDay);
            }
            if (birthList.containsKey(workDay)) {
                countBirth = birthList.get(workDay);
            }
            System.out.printf("Дата %s: количество свадеб - %s, количество разводов - %s, количество рождений - %s\n",
                    workDay.toString(), countMarriage, countDivorce, countBirth);
        }
    }

    public List<WriterTypeRegistry> getRecords() {
        return records;
    }

    private void setElementToMap(Map<LocalDate, Integer> map, LocalDate date) {
        if (map.size() == 0) {
            map.put(date, 1);
        } else if (map.containsKey(date)) {
            Integer value = map.get(date) + 1;
            map.put(date, value);
        } else {
            map.put(date, 1);
        }
    }
}
