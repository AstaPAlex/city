package org.javaacademy.civil.zags;

import static org.javaacademy.civil.zags.TypeRegistry.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CivilRegistry {
    @NonNull
    String name;
    Map<LocalDate, List<WriterTypeRegistry>> listRegistry = new TreeMap<>(LocalDate::compareTo);

    public void birthChild(@NonNull Citizen father, @NonNull Citizen mother, @NonNull LocalDate date) {
        addRegistry(BIRTH_REGISTRATION, date, father, mother);
    }

    public void registrationMarriage(@NonNull Citizen man, @NonNull Citizen woman, @NonNull LocalDate date) {
        man.setFamilyStatus(FamilyStatus.MARRIED);
        man.setMarriagePartner(woman);
        woman.setFamilyStatus(FamilyStatus.MARRIED);
        woman.setMarriagePartner(man);
        addRegistry(MARRIAGE_REGISTRATION, date, man, woman);
    }

    public void registrationDivorce(@NonNull Citizen man, @NonNull Citizen woman, @NonNull LocalDate date) {
        man.setFamilyStatus(FamilyStatus.DIVORCED);
        man.setMarriagePartner(null);
        woman.setFamilyStatus(FamilyStatus.DIVORCED);
        woman.setMarriagePartner(null);
        addRegistry(DIVORCE_REGISTRATION, date, man, woman);
    }

    private void addRegistry(TypeRegistry typeRegistry, LocalDate date,
                             Citizen... citizens) {
        if (!listRegistry.containsKey(date)) {
            listRegistry.put(date, new ArrayList<>());
        }
        listRegistry.get(date).add(new WriterTypeRegistry(date, typeRegistry, citizens));
    }

    public void getReport() {
        System.out.printf("Статистика по ЗАГС: %s\n", name);
        listRegistry.keySet()
                 .forEach(date -> {
                     System.out.printf("Дата %s: количество свадеб - %d,  количество разводов - %d, "
                                     + "количество рождений - %d\n", date, getCount(date, MARRIAGE_REGISTRATION),
                                     getCount(date, DIVORCE_REGISTRATION), getCount(date, BIRTH_REGISTRATION));
                 });
    }

    private long getCount(@NonNull LocalDate date, @NonNull TypeRegistry typeRegistry) {
        return listRegistry.get(date).stream()
                .filter(writer -> typeRegistry.equals(writer.getTypeRegistry()))
                .count();
    }

}
